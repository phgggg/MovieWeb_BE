package com.example.MovieWebsite.services.impl;

import com.example.MovieWebsite.entity.UserEntity;
import com.example.MovieWebsite.services.BaseService;
import com.example.MovieWebsite.services.UserService;
import com.example.MovieWebsite.web.dto.response.ArrayResultDTO;
import com.example.MovieWebsite.web.dto.response.BaseResultDTO;
import com.example.MovieWebsite.web.dto.response.SingleResultDTO;
import com.example.MovieWebsite.web.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl extends BaseService implements UserService {
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public BaseResultDTO findAllUsers(Integer pageSize, Integer page) {
        logger.info("=== START FIND ALL USERS::");
        ArrayResultDTO<UserDTO> responseResultDTO = new ArrayResultDTO<>();
        List<UserDTO> lstUsers = new ArrayList<>();
        try {
            Page<UserEntity> rawDatas = userRepository.findAll(PageRequest.of(page, pageSize));//TODO Cai Pageable gi day xem o day
            if (rawDatas != null) {
                if (!rawDatas.getContent().isEmpty()) {
                    rawDatas.getContent().forEach(user -> {
                        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
                        lstUsers.add(userDTO);
                    });
                }
                responseResultDTO.setSuccess(lstUsers, rawDatas.getTotalElements(), rawDatas.getTotalPages());
                logger.info("=== FIND ALL USERS RESPONSE::" + responseResultDTO.getErrorCode());
            }
        } catch (Exception ex) {
            responseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO addUser(UserDTO userDTO) {
        logger.info("=== START ADD NEW USER::");
        SingleResultDTO<Integer> responseResultDTO = new SingleResultDTO<>();
        try {
            if ((userRepository.findUserEntityByUserName(userDTO.getUserName())) != null) {
                responseResultDTO.setFail("-3", "người dùng này đã tồn tại !!!!");
                logger.info("=== ADD NEW USER STOP RESPONES: " + responseResultDTO.getErrorCode());
                return responseResultDTO;
            }
            if ((userRepository.findUsersEntityByEmail(userDTO.getEmail())) != null) {
                responseResultDTO.setFail("-4", "Email đã được sử dụng !!!!");
                logger.info("=== ADD NEW USER STOP RESPONES: " + responseResultDTO.getErrorCode());
                return responseResultDTO;
            }
            System.out.println("a\t\t"+userDTO.getUserName()+"\t\t");
            System.out.println("a\t\t"+userDTO.getEmail()+"\t\t");
            System.out.println("a\t\t"+userDTO.getPassword()+"\t\t");
            userDTO.setPassword(encoder.encode(userDTO.getPassword()));

            UserEntity user = modelMapper.map(userDTO, UserEntity.class);
            user = userRepository.save(user);
            if (user != null) {
                logger.info("new user" + user.getUserID());
                responseResultDTO.setSuccess(user.getUserID());
            }
            logger.info("=== ADD NEW USER RESPONSE::" + responseResultDTO.getErrorCode());
        } catch (Exception ex) {
            responseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return responseResultDTO;
    }

    @Override
    public BaseResultDTO updateUser(UserDTO userDTO) {
        logger.info("=== START UPDATE USER::" + userDTO.getUserID());
        BaseResultDTO baseResultDTO = new BaseResultDTO();
        String pass = null;
        try {
            UserEntity user = userRepository.getUserEntitiesByUserID(userDTO.getUserID());
            if (user.getUserID() != null) {
                pass = user.getPassword();
                logger.info("OLD PASSWORD IS " + pass);

                UserEntity usersEntity = modelMapper.map(userDTO, UserEntity.class);
                if(userDTO.getPassword()==""){
                    logger.info("PASSWORD UNCHANGED");
                }else{
                    usersEntity.setPassword(encoder.encode(pass));
                }
//                user.setUserName(usersDTO.getUserName());
//                user.setFullName(usersDTO.getFullName());
//                user.setPassWord(usersDTO.getPassWord());
                userRepository.save(usersEntity);
                baseResultDTO.setSuccess();
            }
            logger.info("=== UPDATE USER RESPONSE::" + baseResultDTO.getErrorCode());
        } catch (Exception ex) {
            baseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return baseResultDTO;
    }

    @Override
    public BaseResultDTO findUserEntityByUserName(String userName) {
        logger.info("=== START SEARCH USER NAME");
        SingleResultDTO<UserEntity> resultDTO = new SingleResultDTO<>();
        try {
            UserEntity user = userRepository.getUserByUserName(userName);
            if (user != null) {
                resultDTO.setSuccess(user);
            }
            logger.info("=== SEARCH USERNAME RESPONSE:" + resultDTO.getErrorCode());
        } catch (Exception e) {
            resultDTO.setFail(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return resultDTO;
    }

    @Override
    public BaseResultDTO deleteUser(Integer id) {
        BaseResultDTO baseResultDTO = new BaseResultDTO();
        try {
            UserEntity usersEntity = userRepository.getUserEntitiesByUserID(id);
            if (usersEntity != null) {
                userRepository.delete(usersEntity);
                baseResultDTO.setSuccess();
                logger.info("DELETE USER REPONSE" + baseResultDTO.getErrorCode());
            }
        } catch (Exception e) {
            logger.error("DELETE USER ERR" + e.getMessage(), e);
            baseResultDTO.setFail(e.getMessage());
        }
        return baseResultDTO;
    }

    @Override
    public BaseResultDTO findUserbyId(Integer id) {
        SingleResultDTO<UserEntity> singleResultDTO = new SingleResultDTO<>();
        try {
            UserEntity usersEntity = userRepository.findUserEntityByUserID(id);
            if (usersEntity != null) {
                singleResultDTO.setSuccess(usersEntity);
            }
        } catch (Exception e) {
            logger.error("FIND USER BY ID ERR");
            singleResultDTO.setFail(e.getMessage());
        }
        return singleResultDTO;
    }

    @Override
    public BaseResultDTO findAll() {
        ArrayResultDTO<UserDTO> response = new ArrayResultDTO<>();
        ArrayList<UserDTO> DTOResult = new ArrayList<>();
        try {
            ArrayList<UserEntity> ls = (ArrayList<UserEntity>) userRepository.findAll();
            if (!ls.isEmpty()) {
                for(UserEntity user : ls){
                    UserDTO tempDTO = modelMapper.map(user, UserDTO.class);
                    DTOResult.add(tempDTO);
                }
                response.setSuccess(DTOResult);
            }
        } catch (Exception e) {
            response.setFail(e.getMessage());
        }
        return response;
    }

    @Override
    public BaseResultDTO login(String userName, String password) {
        SingleResultDTO<UserEntity> singleResultDTO = new SingleResultDTO<>();
        logger.info("FIND USER");
        logger.info("USERNAME " + userName);
        logger.info("PASSWORD " + password);
        try {
            UserEntity usersEntity = userRepository.findUserEntityByUserName(userName);
            if (usersEntity != null && encoder.matches(password, usersEntity.getPassword())) {
                logger.info("LOGIN SUCCESS");
                singleResultDTO.setSuccess(usersEntity);
            }
        } catch (Exception e) {
            logger.error("LOGIN ERR");
            singleResultDTO.setFail(e.getMessage());
        }
        return singleResultDTO;
    }
}
