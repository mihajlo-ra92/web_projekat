Vue.component("show-membership-options", {
	data: function () {
		    return {
				currentUser: null,
				isSelected: false,
				selected: ''
		    }
	},
	
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div v-if="currentUser != null">
		<div v-if="currentUser.role.includes('BUYER')">
			<div v-if="!isSelected">
				<h3>Daily membership</h3>
				<br>
				<p>Lasts one day, costs 500 rsd</p>
				<br>
				<button type="button" v-on:click="buyDaily()">Buy</button>
				<br>
				<br>
				
				<h3>Monthly membership, costs 2500 rsd</h3>
				<br>
				<p>Lasts one month, costs 500 rsd</p>
				<br>
				<button type="button" v-on:click="buyMonthly()">Buy</button>
				<br>
				<br>
				
				<h3>Yearly membership, costs 10000 rsd</h3>
				<br>
				<p>Lasts one year, costs 500 rsd</p>
				<br>
				<button type="button" v-on:click="buyYearly()">Buy</button>
				<br>
				<br>
			</div>
		</div>
		
		<div v-if="currentUser.role.includes('BUYER')">
			<div v-if="isSelected">
				<div v-if="selected === 'DAILY'">
				 <h3>Daily membership</h3>
				 <p>Price: 500rds</p>
				 <br>
				 <p>Number of workouts: 1</p>
				</div>
				
				<div v-if="selected === 'MONTHLY'">
				 <h3>Monthly membership</h3>
				 <p>Price: 2500rds</p>
				 <br>
				 <p>Number of workouts: 31</p>
				</div>
				
				<div v-if="selected === 'YEARLY'">
				 <h3>Yearly membership</h3>
				 <p>Price: 10000rds</p>
				 <br>
				 <p>Number of workouts: 365</p>
				</div>
				
				<br>
				<button type="button" v-on:click="cancel()">Cancel</button>
				<button type="button" v-on:click="activate()">Activate</button>
				
			</div>
		</div>
	</div>
	
</div>
`
	,
	methods : {
		buyDaily(){
			console.log("Buy daily selected!");
			this.selected = 'DAILY';
			this.isSelected = true;
			
		},
		buyMonthly(){
			console.log("Buy monthly selected!");
			this.selected = 'MONTHLY';
			this.isSelected = true;
		},
		buyYearly(){
			console.log("Buy yearly selected!");
			this.selected = 'YEARLY';
			this.isSelected = true;
		},
		cancel(){
			console.log("Cancel selected!");
			this.isSelected = false;
		},
		activate(){
			console.log("Activate selected!");
			axios
			    .post('/rest/activate', this.selected)
			    .then(response => {
					if (response.data === false){
						toast("Failed!");
					}
					else {
						toast("Succesfull!");
					}
				})
		    	.catch((error) => console.log(error));
			router.push('/my-profile');
		}
	},
	mounted () {
        console.log("Mounted showMembershipOptions");
        axios
			.get('rest/getCurrentUser')
      		.then(response => {
				if (response.data == '404'){
					console.log('No loged in user!');
				}
				else {
					this.currentUser = response.data;
				}
			})
    }
});