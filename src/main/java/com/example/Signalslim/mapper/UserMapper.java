package com.example.Signalslim.mapper;

import com.example.Signalslim.dto.UserDTO;
import com.example.Signalslim.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Créer une instance du mapper
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Conversion de User à UserDTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "email", target = "email") // Ajout de la conversion de l'email
    UserDTO userToUserDTO(User user);

    // Conversion de UserDTO à User
    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "email", target = "email") // Ajout de la conversion de l'email
    User userDTOToUser(UserDTO userDTO);
}
