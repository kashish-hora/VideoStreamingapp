package com.stream.app.reositories;

import com.stream.app.Entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface videorepository extends JpaRepository<Video,String> {
    Optional<Video> findByTitle(String title);


}
