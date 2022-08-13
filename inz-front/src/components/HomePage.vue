<template>
<section class="bg-dark text-light p-5 text-center text-sm-start">
<br>
<br>
<br>

  <div class = "container pt-100" id="forma">
    <div class = "d-sm-flex align-items-center justify-content-between">
   <div class="form-group">
    <label for="exampleFormControlSelect1"> Select motherboard</label>
    <select class="form-control" id="exampleFormControlSelect1"  v-model="selectedMotherboard">
      <option v-for="m in motherboards" v-bind:value="m" :key="m"> {{m}}</option>
    </select>
  
    <br>
    <label for="exampleFormControlSelect1">Select upgrade</label>
    <select class="form-control" id="exampleFormControlSelect1" v-model="selectedUpgrade">
      <option>CPU</option>
      <option>RAM</option>
    </select>
    <br>
    <br>
    <div class="col-md-8">
      <button type="submit" class="btn btn-success" v-on:click="Submit()">SUBMIT</button>
    </div>
<div v-if="view==true && selectedUpgrade=='RAM'">
 <label for="exampleFormControlSelect1">RAM</label>
  <table class="table table-striped table-dark">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Ram capacity</th>
      <th scope="col">Ram frequency</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="(ram, index) in allRams" :key="ram.id">
      <th scope="row">{{index+1}}</th>
      <td>{{ram.name}}</td>
      <td>{{ram.ramCapacity}}</td>
      <td>{{ram.ramFrequency}}</td>
    </tr>
   
  </tbody>
</table>
</div>
  </div>
  </div>
      
     
  </div>
</section>

</template>
<script>

import axios from "axios";
export default{
   data(){
        return{
            selectedMotherboard: '',
             motherboards : new Array(),
             selectedUpgrade: '',
             view : false,
             allRams: new Array()


        }
    },
    mounted(){
      axios.get('http://localhost:8081/query/getAllMotherboards')
          .then(response1 => {
            console.log("motherboards ", response1.data)
            this.motherboards = response1.data;
            if(this.motherboards.length>0){
              this.selectedMotherboard = this.motherboards[0];
            }

          }).catch(error => {
    console.log(error.response)
          })


    },
    methods:
    {
      Submit()
      {
         console.log("RAM:  ", this.selectedUpgrade)
        if(this.selectedMotherboard == '' || this.selectedUpgrade=='')
        {
          alert('Please select all fields')
          return;
        }
        if(this.selectedUpgrade == 'RAM')
        {
         
        axios.get('http://localhost:8081/query/RAM/:'+this.selectedMotherboard)
        .then(response =>{
          this.allRams=response.data;
          this.view=true;
          console.log("all Rams ", response.data)
        }).catch(error => {
    console.log(error.response)
          })

      }
      }


    }
}


</script>


<style>

#home{   
  margin: 0 auto;
  text-align: center;
}

#home button{
	padding:10px;
	border:none;
	background-color:#2ECC71;
	color:#fff;
	font-weight:600;
	border-radius:20px;
	width:600px;
  height:50px;

}
 #forma{
      text-align:center;
  }


#home button:hover{
	background: #333;
  }

</style>