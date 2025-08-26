package Streaming.Application.Video.repository;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;


// is JpaRep
@Repository
public class VideoRepository extends JpaRepositoriesAutoConfiguration {
    Optional<Video> findByTitle(String title);

    // query method
    //native Query createNativeQuery(String sql);
    // criteria query api


}
