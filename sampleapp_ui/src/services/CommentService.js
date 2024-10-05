import http from "../http-common";

class CommentService {
  getAllComments(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/comment/comments`, searchDTO);
  }

  get(commentId) {
    return this.getRequest(`/comment/${commentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/comment?field=${matchData}`, null);
  }

  addComment(data) {
    return http.post("/comment/addComment", data);
  }

  update(data) {
  	return http.post("/comment/updateComment", data);
  }
  
  uploadImage(data,commentId) {
  	return http.postForm("/comment/uploadImage/"+commentId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new CommentService();
