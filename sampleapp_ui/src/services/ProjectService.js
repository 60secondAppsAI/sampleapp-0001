import http from "../http-common";

class ProjectService {
  getAllProjects(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/project/projects`, searchDTO);
  }

  get(projectId) {
    return this.getRequest(`/project/${projectId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/project?field=${matchData}`, null);
  }

  addProject(data) {
    return http.post("/project/addProject", data);
  }

  update(data) {
  	return http.post("/project/updateProject", data);
  }
  
  uploadImage(data,projectId) {
  	return http.postForm("/project/uploadImage/"+projectId, data);
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

export default new ProjectService();
