package org.abhi.spring.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.abhi.spring.model.Movie;
import org.abhi.spring.service.GetRating;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//this annotation tells front controller that this is controller class
@Controller
// with annotation you don't need to extend one of base controller class of
// spring
public class HelloController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView helloWorld() throws Exception {
		ModelAndView modelandview = new ModelAndView("welcome");
		return modelandview;
	}

	@RequestMapping(value = "/showrating.do", method = RequestMethod.POST)

	public ModelAndView hi(@RequestParam("names") String names, @RequestParam("textNames") String textNames)
			throws UnsupportedEncodingException // new annotation
	{

		textNames += names;
		textNames = textNames.replaceAll("<br>", "\n");
		System.out.println(textNames);
		String[] movieNames = textNames.split("\n");
		GetRating rating = new GetRating();
		rating.setNames(movieNames);
		Movie[] movies;
		movies = rating.getMovies();
		Arrays.sort(movies);
		ModelAndView modelandview = new ModelAndView("ShowRating", "movies", movies);
		// "hi" is variable name which will be recognized by jsp
		return modelandview;
	}

	/*
	 * @RequestMapping("/welcome/{username}/{country}") //instead of putting
	 * each path variable in anotation we will use map public ModelAndView
	 * hi(@PathVariable Map<String,String> map) //putting map in path variable
	 * annotation also require us to put "mvc:annotation:driven" in servlet
	 * dispatcher { String name=map.get("username");//gives us value of this
	 * variable String country=map.get("country");//gives us value of this
	 * variable
	 * 
	 * ModelAndView modelandview=new ModelAndView("HelloPage");
	 * modelandview.addObject("hi", "hello "+name+" you are form "
	 * +country);//"hi" is variable name which will be recognized by jsp return
	 * modelandview; }
	 */
}
