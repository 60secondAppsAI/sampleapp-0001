
<template>
  <div class="content">
    <!-- search bar -->
    <div class="row my-3">
      <div class="col-8"></div>
      <div class="col-4">
        <!-- Header Search Input -->
        <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Search by BusinessUnit#, Location#, Operator#, City, State, FirstName, LastName…"
          @search="onSearch" :loading='searchLoading' v-model="searchQuery">
          <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z"
              fill="#111827" />
          </svg>
        </a-input-search>
        <!-- / Header Search Input -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <card>
          <template slot="header">
            <div class="row justify-content-between align-items-between mx-3">

              <h5 class="card-title">Comments</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalComments = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalComments">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add Comment</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="CommentId" type="text" placeholder="Enter CommentId" v-model="commentToAdd.commentId"></base-input>
  <base-input label="Content" type="text" placeholder="Enter Content" v-model="commentToAdd.content"></base-input>
  <base-input label="CreatedDate" type="text" placeholder="Enter CreatedDate" v-model="commentToAdd.createdDate"></base-input>
  <base-input label="IssueId" type="text" placeholder="Enter IssueId" v-model="commentToAdd.issueId"></base-input>
  <base-input label="UserId" type="text" placeholder="Enter UserId" v-model="commentToAdd.userId"></base-input>
                  </form>
                </div>
                <template slot="footer">
                  <base-button type="primary" @click="handleAddSubmitted()">Save</base-button>
                </template>
              </modal>
            </div>
          </template>


          <!-- Conditionally render the view based on the 'isTableView' flag -->
          <div v-if="isTableView">
            <!-- Render the existing Table View -->
            <a-table :columns="columns" :data-source="comments" :row-key="record => record.CommentId" :pagination="pagination"
              :loading="searchLoading" @change="onTableChange" :scroll="{ x: 'max-content' }">



             <template slot="lastModified" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="createdOn" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="blackOutStartDate" slot-scope="dataIndex">
              	{{ formatDate(dataIndex) }}
              </template>
            </a-table>
          </div>
          <div v-else>
            <!-- Render the Picture View  -->
            <CommentPictureView :comments="comments" />
          </div>

        </card>
      </div>
    </div>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import BaseButton from "@/components/BaseButton";
import BaseInput from "@/components/Inputs/BaseInput";
import NotificationTemplate from "@/pages/Notifications/NotificationTemplate";
import { Card } from "@/components/index";
import CommentService from "../services/CommentService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import CommentPictureView from './CommentPictureView.vue';


const commentsColumns = [
  "commentId",
  "year",
  "date",
  "competitionId",
  "commentId"
]

export default {
  props: {
    comments: {
      type: Array,
      required: true,
    },
    totalElements: {
      type: Number,
      required: true,
    },
    page: {
      type: Number,
      required: true,
    },
  },
  components: {
    Card,
    Modal,
    BaseButton,
    BaseInput,
    Table,

    Pagination,
    CommentPictureView
  },

  data() {
    return {
      modalComments: false,
        isTableView: true,

      columns: [
        {
          title: 'Comment Id',
		dataIndex: 'commentId',
          visible: true,
          scopedSlots: { customRender: 'commentId' },
          sorter: true
          //sorter: (a, b) => a.commentId - b.commentId,
          //sorter: (a, b) => a.commentId.localeCompare(b.commentId),
        },
        {
          title: 'Content',
		dataIndex: 'content',
          visible: true,
          scopedSlots: { customRender: 'content' },
          sorter: true
          //sorter: (a, b) => a.content - b.content,
          //sorter: (a, b) => a.content.localeCompare(b.content),
        },
        {
          title: 'Created Date',
		dataIndex: 'createdDate',
          visible: true,
          scopedSlots: { customRender: 'createdDate' },
          sorter: true
          //sorter: (a, b) => a.createdDate - b.createdDate,
          //sorter: (a, b) => a.createdDate.localeCompare(b.createdDate),
        },
        {
          title: 'Issue Id',
		dataIndex: 'issueId',
          visible: true,
          scopedSlots: { customRender: 'issueId' },
          sorter: true
          //sorter: (a, b) => a.issueId - b.issueId,
          //sorter: (a, b) => a.issueId.localeCompare(b.issueId),
        },
        {
          title: 'User Id',
		dataIndex: 'userId',
          visible: true,
          scopedSlots: { customRender: 'userId' },
          sorter: true
          //sorter: (a, b) => a.userId - b.userId,
          //sorter: (a, b) => a.userId.localeCompare(b.userId),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} comments`,
      },

      comments: [],
      commentToAdd: {},

      commentsTable: {
        columns: [...commentsColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'commentId',           // Column to sort by
      sortOrder: 'asc',     // Sort order (asc or desc)
      searchQuery: '',      // Search query
      searchLoading: false, // Initialize searchLoading property


    };
  },
  watch: {
    searchQuery: {
      handler: "handleSearchQueryChanged", // Call the fetchData method when searchQuery changes
      immediate: true, // Trigger immediately when the component is mounted
    },
  },

  methods: {

    async renderCommentsTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let commentsTableData = [];
      for (let i = 0; i < this.comments.length; i++) {
        commentsTableData.push({
          id: i,
          commentId: this.comments[i].commentId,
          year: this.comments[i].year,
          date: this.comments[i].date,
          competitionId: this.comments[i].competitionId,
          commentId: this.comments[i].commentId,
        });

      }
      this.searchLoading = false;
    },

    async onTableChange(pagination, filters, sorter) {
      if (sorter && sorter.field && sorter.order) {
        this.sortBy = sorter.field;
        if (sorter.order == "ascend") {
            this.sortOrder = "asc";
        } else {
            this.sortOrder = "desc";
        }
      }
      if (pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
      }

	  this.$emit('get-all-comments',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
      this.handleTableChanged()
    },
	
	initializeColumns() {
        this.columns = this.columns.filter(item => item.visible);
    },

    routingToUserDetail(id) {
      this.$router.push({ name: 'UserDetail', params: { userId: id.toString() }})
    },
    routingToIssueDetail(id) {
      this.$router.push({ name: 'IssueDetail', params: { issueId: id.toString() }})
    },
    routingToCommentDetail(id) {
      this.$router.push({ name: 'CommentDetail', params: { commentId: id.toString() }})
    },
    routingToProjectDetail(id) {
      this.$router.push({ name: 'ProjectDetail', params: { projectId: id.toString() }})
    },
    routingToTaskDetail(id) {
      this.$router.push({ name: 'TaskDetail', params: { taskId: id.toString() }})
    },
    routingToAttachmentDetail(id) {
      this.$router.push({ name: 'AttachmentDetail', params: { attachmentId: id.toString() }})
    },
    
    handleSearchQueryChanged() {
    	console.log("handleSearchQueryChanged CALLED FROM @search")
    	this.$root.$emit('searchQueryForCommentsChanged', this.searchQuery);
		//this.renderCommentsTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalComments = false;

      const currentDate = new Date().getTime();
      this.commentToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.commentToAdd);
      console.log(jsonData);
      
      const res = await CommentService.addComment(jsonData);

      if (res.status === 200) {
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          type: "success",
          timeout: 3000,
        });
      }
	},
	
	handleTableChanged() {
	},
	
	formatTime(dateString) {
      if(dateString !== null){
        const date = new Date(dateString);
      return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}-${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} EST`;
      }
      // Format the date here as needed, for example:
    },  
    
 formatDate(dateString) {
    if (dateString !== null) {
	    const date = new Date(dateString);
	    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	    const day = String(date.getDate()).padStart(2, '0');
	    const monthAbbreviation = months[date.getMonth()];
	    const year = date.getFullYear();
	    return `${day} ${monthAbbreviation} ${year}`;
  	}
  	// Handle the case when dateString is null or invalid
  	return '';
    
   },

  },
  mounted() {
  	this.initializeColumns();
    this.renderCommentsTable();
  }
};
</script>

<style>
.modal-dialog {
  margin-top: -300px;
}
.ant-table-row  {
	text-align: center;
}
.ant-table-thead th span {
	text-align: center;
	width: 100%
}
.ant-table-fixed td span {
    text-align: center;
}

</style>
