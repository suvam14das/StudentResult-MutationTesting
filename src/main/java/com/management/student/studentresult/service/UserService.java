package com.management.student.studentresult.service;

import com.management.student.studentresult.dao.Auth;
import com.management.student.studentresult.dao.Role;
import com.management.student.studentresult.dao.User;
import com.management.student.studentresult.repository.AuthRepository;
import com.management.student.studentresult.repository.UserRepository;
import com.management.student.studentresult.utils.UserDetailsSecurity;
import com.management.student.studentresult.vo.ResponseMessage;
import com.management.student.studentresult.vo.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.ParseException;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService{
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private RoleService roleService;

    public User saveUser(User user){
        return repository.save(user);
    }

    public List<User> getUsers(){
        return repository.findAll();
    }

    public User getUserById(int id){
        return repository.findById(id).orElse(null);
    }

    public User getUserByExtId(String extId){
        return repository.findByExtId(extId);
    }

    public UserDetails getUserDetailsByExtId(String extId) throws ParseException {

        User user = getUserByExtId(extId);
        UserDetails userDetails = new UserDetails();
        userDetails.setName(user.getName());
        userDetails.setExtId(user.getExtId());
        userDetails.setAddress(user.getAddress());
        userDetails.setContactno(user.getPhone());
        userDetails.setEmail(user.getAuth().getEmail());

        return userDetails;
    }
    
    public ResponseEntity<ResponseMessage> registrationService(UserDetails userDetails){
    	
    	try {
			if(authService.getAuthByEmail(userDetails.getEmail()).isPresent())
				throw new Exception("Email already exists!");
			if(repository.existsByExtId(userDetails.getExtId()))
				throw new Exception("We have another user with same Id!");
			if(repository.existsByPhone(userDetails.getContactno()))
				throw new Exception("This contact number is already taken!");
			Auth auth = new Auth(userDetails.getEmail(), userDetails.getPassword());
			auth=authService.saveAuth(auth);
			Role role = roleService.getRoleByName(userDetails.getRole());
			User user = new User(auth, role, userDetails.getExtId(), userDetails.getName(), userDetails.getAddress(), userDetails.getContactno(), userDetails.getDob());
			saveUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(e.getMessage()));
		}
    	String message="Registration Successful!";
    	return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

    }

	@Override
	public UserDetailsSecurity loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
			User user=repository.findByExtId(username);
			if(user.equals(null))
				throw new UsernameNotFoundException("The username"+username+"does not exist!");
			return new UserDetailsSecurity(user);
	}

}
