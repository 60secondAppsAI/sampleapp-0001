<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <project-table
            v-if="projects && projects.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:projects="projects"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-projects="getAllProjects"
             >

            </project-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import ProjectTable from "@/components/ProjectTable";
import ProjectService from "../services/ProjectService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ProjectTable,
  },
  data() {
    return {
      projects: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllProjects(sortBy='projectId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ProjectService.getAllProjects(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.projects.length) {
					this.projects = response.data.projects;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching projects:", error);
        }
        
      } catch (error) {
        console.error("Error fetching project details:", error);
      }
    },
  },
  mounted() {
    this.getAllProjects();
  },
  created() {
    this.$root.$on('searchQueryForProjectsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllProjects();
    })
  }
};
</script>
<style></style>
