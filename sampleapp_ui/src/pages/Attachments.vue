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
            <attachment-table
            v-if="attachments && attachments.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:attachments="attachments"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-attachments="getAllAttachments"
             >

            </attachment-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import AttachmentTable from "@/components/AttachmentTable";
import AttachmentService from "../services/AttachmentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    AttachmentTable,
  },
  data() {
    return {
      attachments: [],
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
    async getAllAttachments(sortBy='attachmentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await AttachmentService.getAllAttachments(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.attachments.length) {
					this.attachments = response.data.attachments;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching attachments:", error);
        }
        
      } catch (error) {
        console.error("Error fetching attachment details:", error);
      }
    },
  },
  mounted() {
    this.getAllAttachments();
  },
  created() {
    this.$root.$on('searchQueryForAttachmentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllAttachments();
    })
  }
};
</script>
<style></style>
