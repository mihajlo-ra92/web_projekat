Vue.component("create-sport-object", {
	data: function () {
		    return {
				currentUser: null
		    }
	},
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		Create sport object:
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
        console.log("Mounted MyProfile");
        axios
			.get('rest/getCurrentUser')
      		.then(response => (this.currentUser = response.data))
    }
});