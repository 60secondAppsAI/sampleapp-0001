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
            <comment-table
            v-if="comments && comments.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:comments="comments"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-comments="getAllComments"
             >

            </comment-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import CommentTable from "@/components/CommentTable";
import CommentService from "../services/CommentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CommentTable,
  },
  data() {
    return {
      comments: [],
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
    async getAllComments(sortBy='commentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CommentService.getAllComments(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.comments.length) {
					this.comments = response.data.comments;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching comments:", error);
        }
        
      } catch (error) {
        console.error("Error fetching comment details:", error);
      }
    },
  },
  mounted() {
    this.getAllComments();
  },
  created() {
    this.$root.$on('searchQueryForCommentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllComments();
    })
  }
};
</script>
<style></style>
