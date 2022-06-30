Vue.component("sport-object", {
	data: function(){
		return{
			currentUser: null,
			trainers :null,
			contents: null,
			selected: false,
			selectedContent:{},
			SportObject : {},
     	      selectedObject: {},
			input: {
				name:"",
				workoutType:"",
				description:"",
				workoutDuration:""				
			}
		}
	},
	
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div v-if="selected != true">
		This sport object:
		<br>
		<div v-if="SportObject  != null">
		<br>
			<p>Name:{{this.SportObject.name}}</p>
			<p>Object type:{{this.SportObject.objectType}}</p>
			<p>Is open:{{this.SportObject.isOpen}}</p>
			<p>Average grade:{{this.SportObject.avegareGrade}}</p>
		
			<table v-if="SportObject.content != null">
				<tr bgcolor="lightgrey">
					<th scope="col">Content name  </th>
					<th scope="col">Content type  </th>
					<th scope="col">duration  </th>
					<th scope="col">description  </th>
					<th scope="col">Trainer  </th>
				</tr>
				
				<tr v-for="so in contents" v-on:click="selectObject(so)">
					<td scope="row">{{so.name}}</td>
					<td scope="row">{{so.workoutType}}</td>
					<td scope="row">{{so.workoutDuration}}</td>
					<td scope="row">{{so.description}}</td>
					<td scope="row">{{so.trainerId}}</td>
				</tr>
			</table>	
			Add content:
			<br>		
			<input type="text" placeholder="Name" v-model="input.name">
			<br>
			<input type="text" v-model="input.workoutType" placeholder="Type of content">
			<br>
			<input type="text" placeholder="Description" v-model="input.description">
			<br>
			<input type="text" placeholder="Duration" v-model="input.workoutDuration">
			<br>
			<button v-on:click="addContent">Add content</button>
		</div>
	</div>	
	
	
	<div v-if="selected != false">
		<table class="table table-bordered table--lg team-roster-table table-hover"">
		<tr bgcolor="lightgrey">
				<th scope="col">Name</th>
				<th scope="col">Surname</th>
		</tr>
		
		<tr v-for="tr in trainers" v-on:click="addTrainerToContent(tr)">
		<td scope="row">{{tr.firstName}}</td>
		<td>{{tr.lastName}}</td>
		</tr>
		</table>
		<button type="button" v-on:click="unselect()">Back</button>
		<button type="button" v-on:click="deleteContent()">Delete</button>
	</div>
	
</div>	  
`,

	methods:{
		deleteContent: function(){
			axios
			.post('rest/deleteContent',this.selectedContent)
			.then(response => console.log(response.data));
			
			axios
			.get('/rest/MenagersSportObject')
			.then(response => {
				this.SportObject  = response.data,
				this.contents = this.SportObject.content});
			this.selected = false;
		},
		getTrainerById : function(trainerId){
			console.log(trainerId);
			return trainerId;
		},
		addTrainerToContent: function(trainer){
			console.log("dodajemo trenera.");
			const request =  this.selectedContent.name + '+' + trainer.id
			axios
			.post('rest/AddTrainerToContent', request)
			.then(response =>(console.log(response.data)))
			.catch((error) => console.log(error));
			
			axios
			.get('/rest/MenagersSportObject')
			.then(response => {
				this.SportObject  = response.data,
				this.contents = this.SportObject.content});
				
			this.selected = false;
		},
		addContent : function(){
			isExist = false;
			console.log("Treba da se doda post za content");
			for(let i = 0; i < this.contents.length; i++){
				if(this.input.name === this.contents[i].name){
					isExist = true;
				}
			}	
			if(isExist === false){				
				axios
				.post('rest/proizvodi/CreateContent', this.input)
				.then(response => console.log("odradjen post"))
				.catch((error) => console.log(error));
				
				axios
			.get('/rest/MenagersSportObject')
			.then(response => {
				this.SportObject  = response.data,
				this.contents = this.SportObject.content});
				
			}else{
				console.log("Vec postoji!");
			}
		},
		selectObject : function(content){
			console.log("Usli smo u select.");
			if(content.trainerId != null){				
				console.log("Vec ima trenera!");
			}							
			this.selectedContent = content;
			this.selected = true;
		},
		unselect : function() {
			console.log("vrati na tabelu!");
			this.selected = false;
			
		}
	}, 
	mounted () {
		console.log("Mounted sport object!");
			axios
			.get('/rest/MenagersSportObject')
			.then(response => {
				this.SportObject  = response.data,
				this.contents = this.SportObject.content});
			
			axios
			.get('rest/getCurrentUser')
      		.then(response => (this.currentUser = response.data));
      		
      		axios
			.get('rest/getAllTrainers')
			.then(response => (this.trainers = response.data));			
		},
});