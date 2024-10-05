import http from "../http-common";

class AttachmentService {
  getAllAttachments(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/attachment/attachments`, searchDTO);
  }

  get(attachmentId) {
    return this.getRequest(`/attachment/${attachmentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/attachment?field=${matchData}`, null);
  }

  addAttachment(data) {
    return http.post("/attachment/addAttachment", data);
  }

  update(data) {
  	return http.post("/attachment/updateAttachment", data);
  }
  
  uploadImage(data,attachmentId) {
  	return http.postForm("/attachment/uploadImage/"+attachmentId, data);
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

export default new AttachmentService();
