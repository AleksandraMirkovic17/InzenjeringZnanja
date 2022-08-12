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
 <label for="exampleFormControlSelect1">CPU</label>
  <table class="table table-striped table-dark">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">First</th>
      <th scope="col">Last</th>
      <th scope="col">Handle</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>Larry</td>
      <td>the Bird</td>
      <td>@twitter</td>
    </tr>
  </tbody>
</table>
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
             motherboards : new Array()

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
        axios.get()
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