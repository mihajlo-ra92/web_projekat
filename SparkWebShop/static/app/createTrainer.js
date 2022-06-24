Vue.component("create-trainer", {
	data: function () {
		    return {
				currentUser: null
		    }
	},
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		Create trainer:
		<br>
	</div>	
	
	
</div>	  
`
	,
	methods : {
		startEdit(){
			console.log("Pushing router to edit profile!");
			router.push('/edit-profile')
		},
		
		startEdit(){
			console.log("Pushing router to create sport object!");
			router.push('/edit-profile')
		}
	},
	mounted () {
        console.log("Mounted create trainer");
        axios
			.get('rest/getCurrentUser')
      		.then(response => (this.currentUser = response.data))
    }
});