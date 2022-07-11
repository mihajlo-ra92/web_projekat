Vue.component("approve-comments", {
	data: function () {
		    return {
				currentUser: null,
				unapprovedComments: null,
				selectedComment: null,
				selected: false
		    }
	},
	
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		<div v-if="currentUser != null">
			<div v-if="currentUser.role.includes('ADMIN')">
				<h3>Unapproved comments</h3>
				<table class="table table-bordered table--lg team-roster-table table-hover"">
					<tr bgcolor="lightgrey">
						<th scope="col">Buyer</th>
						<th scope="col">Sprot object</th>
						<th scope="col">Grade</th>
					</tr>
						
					<tr v-for="com in unapprovedComments" v-on:click="approveComment(com)">
						<td scope="row">{{com.buyer}}</td>
						<td>{{com.sportObject}}</td>
						<td>{{com.grade}}</td>
					</tr>
				</table>
				<div v-if="selected">
					<h4>Comment content: </h4>
					<p>{{selectedComment.content}}</p>
					<br>
					<button type="button" v-on:click="approve()">Approve</button>
					<br>
					<button type="button" v-on:click="deny()">Deny</button>
				</div>
			</div>
		</div>
	</div>	
</div>
`
	,
	methods : {
		refresh: function(){
			console.log("Usli smo u refresh.");
			axios
				.get('rest/get-unapproved-comments')
	      		.then(response => {
						this.unapprovedComments = response.data;
				});
		},
		approveComment: function(comment){
			console.log("Usli smo u approve comment.");
			this.selectedComment= comment;
			this.selected = true;
		},
		approve: function(){
			console.log("Usli smo u approve comment.");
			this.selectedComment.status = "APPROVED";
			axios
			    .post('/rest/update-comment', this.selectedComment)
			    .then(response => {
					toast("APPROVED");
				})
		    	.catch((error) => console.log(error));
		    router.push('/my-profile')
		},
		deny: function(){
			console.log("Usli smo u approve comment.");
			this.selectedComment.status = "DENYED";
			axios
			    .post('/rest/update-comment', this.selectedComment)
			    .then(response => {
					toast("DENYED");
				})
		    	.catch((error) => console.log(error));
		    router.push('/my-profile');
		}
		
	},
	mounted () {
        console.log("Mounted approve comments");
        axios
			.get('rest/getCurrentUser')
      		.then(response => {
				if (response.data == '404'){
					console.log('No loged in user!');
				}
				else {
					this.currentUser = response.data;
				}
			});
		axios
			.get('rest/get-unapproved-comments')
      		.then(response => {
					this.unapprovedComments = response.data;
			})
    }
});