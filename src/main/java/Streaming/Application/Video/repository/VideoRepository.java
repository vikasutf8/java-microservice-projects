package Streaming.Application.Video.repository;


import java.util.Optional;


import org.springframework.stereotype.Repository;
import Streaming.Application.Video.entity.Video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {
    
    // You can define custom query methods here
    // Example: List<Video> findByTitle(String title);

    Optional<Video> findByTitle(String title);
}
