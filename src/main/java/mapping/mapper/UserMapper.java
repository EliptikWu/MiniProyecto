package mapping.mapper;

import domain.model.User;
import mapping.dtos.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto mapFrom(User userMapper){
        return new UserDto(userMapper.getIdUser(),
                userMapper.getName(),
                userMapper.getEmail(),
                userMapper.getTelephone()
                );
    }

    public static User mapFrom(UserDto userMapper){
        return new User(userMapper.idUser(),
                userMapper.name(),
                userMapper.email(),
                userMapper.telephone());
    }

    public static List<UserDto> mapFrom(List<User> userMapper){
        return userMapper.stream().map(UserMapper::mapFrom).collect(Collectors.toList());
    }

    public static List<User> mapFromDto(List<UserDto> userMapper){
        return userMapper.stream().map(UserMapper::mapFrom).collect(Collectors.toList());
    }
}
