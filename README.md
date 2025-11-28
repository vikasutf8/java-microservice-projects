# Apache Kafka 4.x (KRaft Mode) – 3-Node Docker Cluster
Complete Guide: Brokers, Topics, Partitions, Replication, ISR, Failover, Controller Quorum

---

## Overview

This repository provides a production-ready Kafka 4.x cluster in Docker using KRaft mode (no ZooKeeper).  
Includes:

- 3 Kafka brokers  
- KRaft metadata controller quorum  
- Topic and partition setup  
- Producer and consumer CLI usage  
- Leader election and failover demo  
- Kafka internal concepts explained  

Kafka Image: apache/kafka:latest  
ZooKeeper: Not required (KRaft enabled)

---

# Kafka Core Concepts

### Broker
A Kafka server storing partitions and handling producers/consumers.

### Topic
A named data stream (similar to a table).

### Partition
A topic is divided into ordered logs for scalability.

### Leader
Replica handling all reads and writes.

### Follower
Replica that syncs with the leader.

### Replication Factor (RF)
Number of copies of each partition.

### ISR (In-Sync Replicas)
Replicas fully caught up to the leader.  
Only ISR replicas can become leader.

### Min ISR
Minimum number of ISR replicas required for writes.

### Controller
A broker managing metadata, leader elections, and failover.

### KRaft
Kafka Raft Metadata mode replacing ZooKeeper.

---

# Kafka Cluster Architecture

```
Kafka-1 (Node ID 1)
- Broker
- Controller (Active)

Kafka-2 (Node ID 2)
- Broker
- Controller (Standby)

Kafka-3 (Node ID 3)
- Broker
- Controller (Standby)
```

---

# Docker Deployment

## Create project folder

```bash
mkdir kafka-cluster
cd kafka-cluster
```

Create a file:

```bash
nano docker-compose.yml
```

---

# docker-compose.yml

```yaml
version: '3.8'
services:

  kafka-1:
    image: apache/kafka:latest
    container_name: kafka-1
    ports:
      - "9092:9092"
    environment:
      KAFKA_NODE_ID: 1
      KAFKA_PROCESS_ROLES: controller,broker
      KAFKA_CONTROLLER_QUORUM_VOTERS: "1@kafka-1:9093,2@kafka-2:9093,3@kafka-3:9093"
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092,CONTROLLER://0.0.0.0:9093
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka-1:9092
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_LOG_DIRS: /var/lib/kafka/data
    volumes:
      - kafka1-data:/var/lib/kafka/data

  kafka-2:
    image: apache/kafka:latest
    container_name: kafka-2
    ports:
      - "9094:9092"
    environment:
      KAFKA_NODE_ID: 2
      KAFKA_PROCESS_ROLES: controller,broker
      KAFKA_CONTROLLER_QUORUM_VOTERS: "1@kafka-1:9093,2@kafka-2:9093,3@kafka-3:9093"
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092,CONTROLLER://0.0.0.0:9093
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka-2:9092
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_LOG_DIRS: /var/lib/kafka/data
    volumes:
      - kafka2-data:/var/lib/kafka/data

  kafka-3:
    image: apache/kafka:latest
    container_name: kafka-3
    ports:
      - "9095:9092"
    environment:
      KAFKA_NODE_ID: 3
      KAFKA_PROCESS_ROLES: controller,broker
      KAFKA_CONTROLLER_QUORUM_VOTERS: "1@kafka-1:9093,2@kafka-2:9093,3@kafka-3:9093"
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092,CONTROLLER://0.0.0.0:9093
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka-3:9092
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      KAFKA_LOG_DIRS: /var/lib/kafka/data
    volumes:
      - kafka3-data:/var/lib/kafka/data

volumes:
  kafka1-data:
  kafka2-data:
  kafka3-data:
```

---

# Start the Kafka Cluster

```bash
docker-compose up -d
docker ps
```

Expected containers:

- kafka-1  
- kafka-2  
- kafka-3  

---

# Topic Management

## 1. Create topic

```bash
docker exec -it kafka-1 bash

/opt/kafka/bin/kafka-topics.sh \
  --bootstrap-server kafka-1:9092 \
  --create \
  --topic demo-topic \
  --partitions 3 \
  --replication-factor 3
```

---

## 2. Describe topic

```bash
/opt/kafka/bin/kafka-topics.sh \
  --bootstrap-server kafka-1:9092 \
  --describe \
  --topic demo-topic
```

Example output:

```
Partition: 0  Leader: 1  Replicas: 1,2,3  ISR: 1,2,3
Partition: 1  Leader: 2  Replicas: 1,2,3  ISR: 1,2,3
Partition: 2  Leader: 3  Replicas: 1,2,3  ISR: 1,2,3
```

---

# Produce Messages

```bash
/opt/kafka/bin/kafka-console-producer.sh \
  --bootstrap-server kafka-1:9092 \
  --topic demo-topic
```

---

# Consume Messages

```bash
/opt/kafka/bin/kafka-console-consumer.sh \
  --bootstrap-server kafka-2:9092 \
  --topic demo-topic \
  --from-beginning
```

---

# Failover Demonstration

Stop a broker:

```bash
docker stop kafka-1
```

Check new leaders:

```bash
docker exec -it kafka-2 bash

/opt/kafka/bin/kafka-topics.sh \
  --bootstrap-server kafka-2:9092 \
  --describe \
  --topic demo-topic
```

Example output:

```
Partition: 0  Leader: 2  ISR: 2,3
```

---

# Kafka Concept Summary

| Concept | Description |
|--------|-------------|
| Broker | Kafka server |
| Topic | Named message stream |
| Partition | Ordered log |
| Leader | Handles reads/writes |
| Follower | Syncs with leader |
| Replica | Copy of partition |
| ISR | In-sync replicas |
| Min ISR | Required replicas for writes |
| RF | Replication factor |
| Controller | Manages metadata and failover |
| KRaft | Kafka Raft metadata mode |

---

# Extensions

You can add:

- Kafka UI (Kafdrop)
- Schema Registry  
- Kafka Connect  
- Prometheus/Grafana  
- Kubernetes deployment  

---

This Kafka environment supports:

- Replication  
- High Availability  
- Automatic failover  
- KRaft controller quorum  
- Producer and consumer operations  

End of README.
