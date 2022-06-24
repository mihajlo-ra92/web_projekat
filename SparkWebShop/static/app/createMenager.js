Vue.component("create-menager", {
	data: function () {
		    return {
				currentUser: null
		    }
	},
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		Create menager:
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
        console.log("Mounted create menager");
        axios
			.get('rest/getCurrentUser')
      		.then(response => (this.currentUser = response.data))
    }
});