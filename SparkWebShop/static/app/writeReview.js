Vue.component("write-review", {
	data: function () {
		    return {
				currentUser: null,
				visitedObjects: null,
				selected: false,
				selectedObject: null,
				comment: {
					buyer: '',
					sportObject: '',
					content: '',
					grade: 0,
					status: 'WAITING'
				}
		    }
	},
	
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		<div v-if="currentUser != null">
			<div v-if="currentUser.role.includes('BUYER')">
				<h3>Visited sport objects</h3>
				<table class="table table-bordered table--lg team-roster-table table-hover"">
					<tr bgcolor="lightgrey">
						<th scope="col">Name</th>
						<th scope="col">Object type</th>
						<th scope="col">Status</th>
						<th scope="col">Address</th>
						<th scope="col">Grade</th>
					</tr>
						
					<tr v-for="so in visitedObjects" v-on:click="reviewObject(so)">
						<td scope="row">{{so.name }}</td>
						<td>{{so.objectType }}</td>
						<td v-if="so.isOpen">Open</td>
						<td v-else>Closed</td>
						<td>{{so.location.address }}</td>
						<td>{{so.avegareGrade }}</td>
					</tr>
				</table>
				<br>
				<div v-if="selected" >
					<textarea name="comment" v-model="comment.content" placeholder="Write comment" cols="35" rows="7" />
					<br>
					<br>
					<p>Select grade:</p>
					<input type="radio" name="grade" value="1" v-model="comment.grade">1
					<input type="radio" name="grade" value="2" v-model="comment.grade">2
					<input type="radio" name="grade" value="3" v-model="comment.grade">3
					<input type="radio" name="grade" value="4" v-model="comment.grade">4
					<input type="radio" name="grade" value="5" v-model="comment.grade">5
					<br>
				</div>
				<button type="button" v-on:click="submitReview()">Submit review</button>
				<br>
				<button type="button" v-on:click="cancel()">Cancel</button>
				<br>
			</div>
			
		</div>
	</div>	
	
	
</div>
`
	,
	methods : {
		reviewObject: function(sportObject){
			console.log("Usli smo u review object.");
			this.selectedObject = sportObject;
			this.selected = true;
			
		},
		cancel(){
			console.log("Canceled review.");
			this.selectedObject = null;
			this.selected = false;
		},
		submitReview(){
			this.comment.buyer = this.currentUser.username;
			this.comment.sportObject = this.selectedObject.name
			console.log(this.comment);
			this.selected = false;
			axios
			    .post('/rest/submit-comment', this.comment)
			    .then(response => {
					toast(response.data);
				})
		    	.catch((error) => console.log(error));
		}
	},
	mounted () {
        console.log("Mounted write review");
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
          .get('rest/proizvodi/getVisitedSportObjects')
          .then(response => (this.visitedObjects = response.data))
    }
});