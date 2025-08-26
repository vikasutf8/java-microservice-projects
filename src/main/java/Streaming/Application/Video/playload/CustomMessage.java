package Streaming.Application.Video.playload;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Component
@Getter
@Setter
@Builder
public class CustomMessage {
    private String message;
    private boolean success = false;
}
