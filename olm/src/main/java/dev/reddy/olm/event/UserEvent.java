package dev.reddy.olm.event;

import dev.reddy.olm.entity.User;
import dev.reddy.olm.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class UserEvent {

    private User user;
    private EventType eventType;
    private Map<?, ?> data;
}
