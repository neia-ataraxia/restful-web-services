package com.greenfutureinnovations.rest.webservices.restfulwebservices.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.greenfutureinnovations.rest.webservices.restfulwebservices.model.Images;
import com.greenfutureinnovations.rest.webservices.restfulwebservices.repository.EventsRepository;
import com.greenfutureinnovations.rest.webservices.restfulwebservices.repository.ImagesRepository;
import com.greenfutureinnovations.rest.webservices.restfulwebservices.repository.SlidersRepository;

@RestController
@CrossOrigin(origins = "*")
public class ImagesController {

	@Autowired
	private ImagesRepository imagesRepository;

	@Autowired
	private EventsRepository eventsRepository;

	@Autowired
	private SlidersRepository slidersRepository;

	@Autowired
	private UsersController user;

	@GetMapping("/images")
	public List<Images> retrieveAllImages() {
		return imagesRepository.findAll();
	}

	@GetMapping("/images/events/{id}")
	public Images getImagesEvents(@PathVariable long id) {
		System.out.println(imagesRepository.ImagesForEvents().get((int) id - 1).getPath());
		return imagesRepository.ImagesForEvents().get((int) id - 1);
	}
	
	@GetMapping("/images/sliders/{id}")
	public Images getImagesSliders(@PathVariable long id) {
		System.out.println(imagesRepository.ImagesForSliders().get((int) id - 1).getPath());
		return imagesRepository.ImagesForSliders().get((int) id - 1);
	}

	@PutMapping("/images/{id}")
	public ResponseEntity<Images> updateImage(@PathVariable long id, @RequestBody Images image) {
		image.setEdit_date(new Date());
		image.setEditor_id(user.loadUserById());
		imagesRepository.save(image);
		return new ResponseEntity<Images>(image, HttpStatus.OK);
	}

	@PostMapping("/images")
	public ResponseEntity<Void> createImage(@RequestBody Images image) {
		if (image.getType() == 1) {
			image.setRef(slidersRepository.findTopByOrderByIdDesc().getId() + 1);
		} else if (image.getType() == 2) {
			image.setRef(eventsRepository.findTopByOrderByIdDesc().getId() + 1);
		}

		image.setEncoder_id(user.loadUserById());
		Images createImage = imagesRepository.save(image);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createImage.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PostMapping("/savefile/events")
	public void UploadFileEvents(MultipartHttpServletRequest request) throws IOException {
		Iterator<String> itr = request.getFileNames();
		MultipartFile file = request.getFile(itr.next());
		String fileName = file.getOriginalFilename();
		File dir = new File("C:/inetpub/wwwroot/greenfutureinnovations/images/blog/preview");
		if (dir.isDirectory()) {
			File serverFile = new File(dir, fileName);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(file.getBytes());
			stream.close();
		}
	}

	@PostMapping("/savefile/sliders")
	public void UploadFileSliders(MultipartHttpServletRequest request) throws IOException {
		Iterator<String> itr = request.getFileNames();
		MultipartFile file = request.getFile(itr.next());
		String fileName = file.getOriginalFilename();
		File dir = new File("C:/inetpub/wwwroot/greenfutureinnovations/images/full-width-images");
		if (dir.isDirectory()) {
			File serverFile = new File(dir, fileName);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(file.getBytes());
			stream.close();
		}
	}

}
