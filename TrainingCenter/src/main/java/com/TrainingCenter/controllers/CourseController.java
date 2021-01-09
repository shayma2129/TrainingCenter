package com.TrainingCenter.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletContext;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.TrainingCenter.Dto.CourseDto;
import com.TrainingCenter.Dto.PageDto;
import com.TrainingCenter.Dto.UserDto;
import com.TrainingCenter.Model.Entity.Course;
import com.TrainingCenter.services.CourseService;
import com.TrainingCenter.services.CourseUserService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ServletContext context;
	private static String UPLOAD_DIR ="C:\\wamp64\\www\\TrainingCenter";
	
	private final CourseService courseService;
	private final CourseUserService courseUserService;
	
	@Autowired
	public CourseController(CourseService courseService,CourseUserService courseUserService) {
		this.courseService = courseService;
		this.courseUserService=courseUserService;
	}
	
	@PostMapping("/create/{idU}")
	public ResponseEntity<CourseDto> createCourse(@RequestParam(name="data") String data,@RequestParam("photo") MultipartFile photo,@PathVariable("idU") Long userId)throws Exception,IOException,JsonParseException , JsonMappingException  {
		Course course=new ObjectMapper().readValue(data, Course.class);
		String filename= photo.getOriginalFilename();
		String modifiedFileName=course.getTitle_course()+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(filename);
		course.setPhoto(photo.getBytes());
		course.setPath_photo(modifiedFileName);
	    Path rootLocation = Paths.get(UPLOAD_DIR);
	    Files.copy(photo.getInputStream(), rootLocation.resolve(modifiedFileName));
	    course=courseService.createCourse(course,userId);
	    CourseDto courseDto=modelMapper.map(course,CourseDto.class);
		return ResponseEntity.status(HttpStatus.OK).body(courseDto);
	}
	@PutMapping("/update/{idC}")
	public ResponseEntity<CourseDto> updateCourse(@RequestParam(name="data") String data,@RequestParam("photo") MultipartFile photo,@PathVariable("idC") Long courseId)throws Exception,IOException,JsonParseException , JsonMappingException  {
		Course course=new ObjectMapper().readValue(data, Course.class);
		String filename= photo.getOriginalFilename();
		String modifiedFileName=course.getTitle_course()+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(filename);
		course.setPhoto(photo.getBytes());
		course.setPath_photo(modifiedFileName);
	    Path rootLocation = Paths.get(UPLOAD_DIR);
	    Files.copy(photo.getInputStream(), rootLocation.resolve(modifiedFileName));
	    course=courseService.updateCourse(course, courseId);
	    CourseDto courseDto=modelMapper.map(course,CourseDto.class);
		return ResponseEntity.status(HttpStatus.OK).body(courseDto);
	}
	
	@GetMapping("/{courseId}")
    public CourseDto getCourseById(@PathVariable(name = "courseId") Long courseId) {
        return CourseDto.from(this.courseService.getCourseById(courseId));
    }
	 
	@GetMapping(params = {"page", "size" })
    public PageDto<CourseDto> getAllCourses(@RequestParam("page") int page,@RequestParam("size") int size) {
        return PageDto.from(courseService.getAllCourse(page, size).map(CourseDto::from));

    }
	@DeleteMapping("/{courseId}")
	public void deleteCourse(@PathVariable(name = "courseId") Long courseId) {
	        this.courseService.deleteCourse(courseId);
	}
	@GetMapping(params = {"userId","page", "size" })
    public PageDto<CourseDto> getAllCoursesOfStudent(@RequestParam("userId") long userId,@RequestParam("page") int page,@RequestParam("size") int size) {
        return PageDto.from(courseUserService.getAllCourseOfStudent(userId, page, size).map(CourseDto::from));

    }
	@GetMapping(params = {"Iduser","page", "size" })
    public PageDto<CourseDto> getAllCoursesOfSir(@RequestParam("Iduser") long userId,@RequestParam("page") int page,@RequestParam("size") int size) {
        return PageDto.from(courseService.getAllCourseByUserId(userId, page, size).map(CourseDto::from));

    }
	@GetMapping(params = {"Idcourse","page", "size" })
    public PageDto<UserDto> getAllRequestOfSubscribeToOneCourse(@RequestParam("Idcourse") long Idcourse,@RequestParam("page") int page,@RequestParam("size") int size) {
        return PageDto.from(courseUserService.getAllRequestOfCourseId(Idcourse, page, size).map(UserDto::from));

    }
	@GetMapping(params = {"IdC","page", "size" })
    public PageDto<UserDto> getAcceptStudentToOneCourse(@RequestParam("IdC") long Idcourse,@RequestParam("page") int page,@RequestParam("size") int size) {
        return PageDto.from(courseUserService.getAcceptStudentOfCourseId(Idcourse, page, size).map(UserDto::from));

    }
	
	@PutMapping("/{idC}/user/{idU}")
	public void autoriserParticipation(@RequestParam(required=false,name="autorisation") String autorisation,@PathVariable("idC") Long idC,@PathVariable("idU") Long idU) {
    	boolean auto=Boolean.parseBoolean(autorisation);
    	courseUserService.manageSubscribe(idC, idU, auto);
		
	}
	@GetMapping("/{idC}/user/{idU}")
	public void subscribe(@PathVariable("idC") Long idC,@PathVariable("idU") Long idU) {
    	courseUserService.subscribeCourse(idC, idU);
		
	}
	 
}
