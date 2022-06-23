Vue.component("profile", {
	data: function () {
		    return {
		      sportObjects: null,
		      search: ''
		    }
	},
	
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		Profile test:
	</div>		  
</div>
`	  
	, 
	methods : {
		
	},
	mounted () {
		console.log("Mounted profile!");
        axios
          .get('rest/proizvodi/getJustSportObjects')
          .then(response => (this.sportObjects = response.data))
    },
});	  