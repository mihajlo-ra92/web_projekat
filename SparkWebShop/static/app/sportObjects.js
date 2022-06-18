Vue.component("sport-objects", {
	data: function () {
		    return {
		      sportObjects: null,
		      products: null
		    }
	},
	template: ` 
<div>
	Raspoloživi sportski objekti:
	<table border="1">
		<tr bgcolor="lightgrey">
			<th>Naziv</th>
			<th>Tip objekta</th>
			<th>Status</th>
		</tr>
			
		<tr v-for="so in sportObjects">
			<td>{{so.name }}</td>
			<td>{{so.objectType }}</td>
			<td>{{so.isOpen }}</td>
		</tr>
	</table>
	<p>
		<a href="#/sportObjects">Pregled sadržaja korpe</a>
	</p>
</div>		  
`	  
	, 
	methods : {
		addToCart : function (product) {
			axios
			.post('rest/proizvodi/add', {"id":''+product.id, "count":parseInt(product.count)})
			.then(response => (toast('Product ' + product.name + " added to the Shopping Cart")))
		}
	},
	mounted () {
		console.log("Mounted sportObjects!");
        axios
          .get('rest/proizvodi/getJustSportObjects')
          .then(response => (this.sportObjects = response.data))
        },
});	  