<template>
<section class="bg-dark text-light p-5 text-center text-sm-start">
<br>
<br>
<br>

  <div class = "container pt-100" id="forma">
    <div class = "d-sm-flex align-items-center justify-content-between">
   <div class="form-group">
    <label for="problem"> Select problem</label>
    <select class="form-control" id="problem"  v-model="selectedProblem">
      <option v-for="p in problems" v-bind:value="p" :key="p"> {{p}}</option>
    </select>
  
    <br>
    <br>
    <br>
    <div class="col-md-8">
      <button type="submit" class="btn btn-success" v-on:click="Submit()">SUBMIT</button>
    </div>

<div v-if="view==true">
  <table class="table table-striped table-dark">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Cause</th>
      <th scope="col">Probability</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="(c, index) in causes" :key="c.node">
      <th scope="row">{{index+1}}</th>
      <td>{{c.node}}</td>
      <td>{{c.probability + " %"}}</td>
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
             selectedProblem: '',
             problems : new Array(),
             causes : new Array(),
             view : false
        }
    },
    mounted(){
     this.problems = ["not_starting",
                    "no_internet",
                    "slow_internet",
                    "camera_not_working",
                    "speakers_not_working",
                    "mouse_not_working",
                    "monitor_not_working",
                    "printer_not_working",
                    "microphone_not_working",
                    "keyboard_not_working",
                    "hard_disk_not_working",
                    "no_display_output",
                    "program_not_responding",
                    "restart",
                    "not_booting",
                    "bsod",
                    "loud_noise",
                    "overheating",
                    "frozen_screen",
                    "battery_draining"]
    },
    methods:
    {
      Submit()
      {

        // if(this.selectedMotherboard == '' || this.selectedUpgrade=='')
        // {
        //   alert('Please select all fields')
        //   return;
        // }
        // if(this.selectedUpgrade == 'RAM')
        // {
         
        axios.get('http://localhost:8082/bayes/' + this.selectedProblem)
        .then(response =>{
          this.causes = response.data;
          this.view = true;
          console.log("causes", response.data)
        }).catch(error => {
    console.log(error.response)
          })

        

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