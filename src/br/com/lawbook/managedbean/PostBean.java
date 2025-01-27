package br.com.lawbook.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import org.hibernate.HibernateException;

import br.com.lawbook.business.PostService;
import br.com.lawbook.business.ProfileService;
import br.com.lawbook.model.Post;
import br.com.lawbook.util.FacesUtil;

/**
 * @author Edilson Luiz Ales Junior
 * @version 01NOV2011-04
 */
@ManagedBean
@RequestScoped
public class PostBean {
	
	private Post post;
	private String postContent;

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public void savePost(ActionEvent event) {
		this.post = new Post();
		this.post.setContent(this.postContent);
		this.post.setReceiver(ProfileService.getInstance().getPublicProfile());
		try {
			this.post.setReceiver(ProfileService.getInstance().getAuthorizedUserProfile());
			PostService.getInstance().create(post);
			FacesUtil.successMessage("lo/", postContent);
		} catch (IllegalArgumentException e) {
			FacesUtil.errorMessage("", e.getMessage());
		} catch (HibernateException e) {
			FacesUtil.errorMessage("", e.getMessage());
		} catch (Exception e) {
			FacesUtil.errorMessage("", e.getMessage());
		}
	}

}
