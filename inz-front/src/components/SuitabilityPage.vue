<template>
<section class="bg-dark text-light p-5 text-center text-sm-start">
<br>
<br>
<br>

  <div class = "container pt-100" id="forma">
    <div class = "d-sm-flex align-items-center justify-content-between">
   <div class="form-group">
      <label for="exampleFormControlSelect1"> Select CPU</label>
    <select class="form-control" id="exampleFormControlSelect1"  v-model="selectedCPU">
      <option v-for="m in cpus" v-bind:value="m" :key="m"> {{m}}</option>
    </select>
    <br>

      <label for="exampleFormControlSelect1"> Select HDD</label>
    <select class="form-control" id="exampleFormControlSelect1"  v-model="selectedHDD">
      <option v-for="m in hdds" v-bind:value="m" :key="m"> {{m}}</option>
    </select>
    <br>

      <label for="exampleFormControlSelect1"> Select GPU</label>
    <select class="form-control" id="exampleFormControlSelect1"  v-model="selectedGPU">
      <option v-for="m in gpus" v-bind:value="m" :key="m"> {{m}}</option>
    </select>
    <br>

      <label for="ssd_selection"> Select SSD</label>
    <select class="form-control" id="ssd_selection"  v-model="selectedSSD">
      <option v-for="m in ssds" v-bind:value="m" :key="m"> {{m}}</option>
    </select>
	<br>
	
	<label for="psu_selection"> Select PSU</label>
    <select class="form-control" id="psu_selection"  v-model="selectedPSU">
      <option v-for="m in psus" v-bind:value="m" :key="m"> {{m}}</option>
    </select>
	<br>
	
	<div>
	<label for="ram_selection"> Select RAM</label>
		<select class="form-control" id="ram_selection"  v-model="selectedRAM">
			<option v-for="m in rams" v-bind:value="m" :key="m"> {{m}}</option>
		</select>
		<br>
		<input id="number" type="number" v-model="selectedRAMCount">
	</div>
	<br>
    <div class="col-md-8">
      <button type="submit" class="btn btn-success" v-on:click="Submit()">SUBMIT</button>
    </div>
  </div>
  
	<div v-if="view==true">
		<div>Casual: {{suitability.casualScore}}</div>
		<div>Gaming: {{suitability.gamingScore}}</div>
		<div>Mining: {{suitability.miningScore}}</div>
		<div>Hosting: {{suitability.hostingScore}}</div>
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
            allCpus: new Array(),
            selectedCPU: '',
            cpus: new Array(),
            selectedHDD: '',
            hdds: new Array(),
            allHdds: new Array(),
            selectedSSD: '',
            ssds: new Array(),
            allSsds: new Array(),
            selectedGPU: '',
            gpus: new Array(),
            allGpus: new Array(),
            selectedPSU: '',
            psus: new Array(),
            allPsus: new Array(),
            selectedRAM: '',
            rams: new Array(),
            allRams: new Array(),
			selectedRAMCount : 1,
			suitability : {
				casualScore : 0,
				gamingScore : 0,
				miningScore : 0,
				hostingScore : 0
			}
        }
    },
    mounted(){
      axios.get('http://localhost:8082/query/getAllMotherboards')
          .then(response1 => {
            console.log("motherboards ", response1.data)
            this.motherboards = response1.data;
            if(this.motherboards.length>0){
              this.selectedMotherboard = this.motherboards[0];
            }

          }).catch(error => {
    console.log(error.response)
          })

          axios.get('http://localhost:8082/query/getAllCPU')
          .then(response1 => {
            console.log("cpus", response1.data)
            this.cpus = response1.data;
            if(this.cpus.length>0){
              this.selectedCPU = this.cpus[0];
            }

          }).catch(error => {
    console.log(error.response)
          })

          axios.get('http://localhost:8082/query/getAllHDD')
          .then(response1 => {
            console.log("hdds", response1.data)
            this.hdds = response1.data;
			this.hdds.unshift('');
            if(this.hdds.length>0){
              this.selectedHDD = this.hdds[0];
            }

          }).catch(error => {
    console.log(error.response)
          })

          axios.get('http://localhost:8082/query/getAllGPU')
          .then(response1 => {
            console.log("gpus", response1.data)
            this.gpus = response1.data;
            if(this.gpus.length>0){
              this.selectedGPU = this.gpus[0];
            }

          }).catch(error => {
    console.log(error.response)
          })

          axios.get('http://localhost:8082/query/getAllSSD')
          .then(response1 => {
            console.log("ssd", response1.data)
            this.ssds = response1.data;
			this.ssds.unshift('');
            if(this.ssds.length>0){
              this.selectedSSD = this.ssds[0];
            }

          }).catch(error => {
                console.log(error.response)
          })

          axios.get('http://localhost:8082/query/getAllPSU')
          .then(response1 => {
            console.log("psu", response1.data)
            this.psus = response1.data;
            if(this.psus.length>0){
              this.selectedPSU = this.psus[0];
            }

          }).catch(error => {
                console.log(error.response)
          })
          
          axios.get('http://localhost:8082/query/getAllRAM')
          .then(response1 => {
            console.log("ram", response1.data)
            this.rams = response1.data;
            if(this.rams.length>0){
              this.selectedRAM = this.rams[0];
            }

          }).catch(error => {
                console.log(error.response)
          })
    },
    methods:
    {
      Submit()
      {
        let specification = {
            cpu: this.selectedCPU,
            gpu: this.selectedGPU,
            ssd: this.selectedSSD,
            hdd: this.selectedHDD,
            ram: this.selectedRAM,
            psu: this.selectedPSU,
			ramCount: this.selectedRAMCount
        };

        axios.post('http://localhost:8082/suitability', specification)
        .then(response =>{
            this.suitability = response.data;
			this.suitability.casualScore = Math.round(response.data.casualScore * 100) / 100;
			this.suitability.gamingScore = Math.round(response.data.gamingScore * 100) / 100;
			this.suitability.miningScore = Math.round(response.data.miningScore * 100) / 100;
			this.suitability.hostingScore = Math.round(response.data.hostingScore * 100) / 100;
			this.view = true;
            console.log(this.suitability);
        }).catch(error => {
            console.log(error.response);
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