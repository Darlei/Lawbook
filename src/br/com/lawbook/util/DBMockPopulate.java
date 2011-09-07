package br.com.lawbook.util;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.lawbook.model.*;

/**
 * @author Edilson Luiz Ales Junior
 * @version 07SEP2011-04
 * 
 */
@SuppressWarnings("unused")
public class DBMockPopulate {

	private static DBMockPopulate instance;
	private DBMockService dbs;
	private final static Long TABLE_SIZE = 5L;

	public static DBMockPopulate getInstance() {
		if (instance == null) {
			instance = new DBMockPopulate();
		}
		return instance;
	}

	private DBMockPopulate() {
		this.dbs = DBMockService.getInstance();
	}

	protected List<User> generateTableUser() {
		List<User> tableUser = new ArrayList<User>();
		for (Long i = 1L; i <= TABLE_SIZE; i++) {
			tableUser.add(dbs.getNewUser(i));
		}
		return tableUser;
	}

	protected List<Profile> generateTableProfile() {
		List<Profile> tableProfile = new ArrayList<Profile>();
		for (Long i = 1L; i <= TABLE_SIZE; i++) {
			tableProfile.add(dbs.getNewProfile(i * 10L));
		}
		return tableProfile;
	}

	protected List<Location> generateTableLocation() {
		List<Location> tableLocation = new ArrayList<Location>();
		for (Long i = 1L; i <= TABLE_SIZE; i++) {
			tableLocation.add(dbs.getNewLocation(i * 100L));
		}
		return tableLocation;
	}

	protected List<Post> generateTablePost() {
		List<Post> tablePost = new ArrayList<Post>();
		for (Long i = 1L; i <= TABLE_SIZE; i++) {
			tablePost.add(dbs.getNewPost(i * 1000L));
		}
		return tablePost;
	}

	protected List<Comment> generateTableComment() {
		List<Comment> tableComment = new ArrayList<Comment>();
		for (Long i = 1L; i <= TABLE_SIZE; i++) {
			tableComment.add(dbs.getNewComment(i * 10000L));
		}
		return tableComment;
	}
}