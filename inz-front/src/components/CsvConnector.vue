<template class="tem">
  <div class="adventure-registration">
    <form>
      <h3 class="mb-3">Similar computers</h3>
      <div class="col-11">
       <label for="exampleFormControlSelect1">Select DDR</label>
    <select class="form-control" id="exampleFormControlSelect1" v-model="motherboardDDR">
      <option>3</option>
      <option>4</option>
      <option>5</option>
    </select>
    
      </div>

      <div class="col-11">
       <label for="exampleFormControlSelect1">Select socket</label>
    <select class="form-control" id="exampleFormControlSelect1" v-model="motherboardSocket">
      <option>AM4</option>
      <option>LGA1700</option>
    </select>
    
      </div>
 <br>
      <div class="col-11">
        <label for="adventure-name" class="form-label">Hdd memory capacity</label>
        <input type="text" class="form-control" id="adventure-name" placeholder="E.g. Titanic" v-model = "hddMemoryCapacity" required>
      </div>

 <br>
      <div class="col-11">
        <label for="adventure-name" class="form-label">Ram capacity</label>
        <input type="text" class="form-control" id="adventure-name" placeholder="E.g. Titanic" v-model = "ramCapacity" required>
      </div>
   <br>
      <div class="col-11">
        <label for="adventure-name" class="form-label">Turbo Boost</label>
        <input type="text" class="form-control" id="adventure-name" placeholder="E.g. Titanic" v-model = "turboBoost" required>
      </div>
      <br>
      <div class="col-11">
        <label for="adventure-name" class="form-label">Cpu MemorySpeed</label>
        <input type="text" class="form-control" id="adventure-name" placeholder="E.g. Titanic" v-model = "cpuMemorySpeed" required>
      </div>
      <br>
      <div class="col-11">
        <label for="adventure-name" class="form-label">Gpu Teraflops</label>
        <input type="text" class="form-control" id="adventure-name" placeholder="E.g. Titanic" v-model = "gpuTeraflops" required>
      </div>

      <div class="col-11">
        <label for="adventure-name" class="form-label">Ram Frequency</label>
        <input type="text" class="form-control" id="adventure-name" placeholder="E.g. Titanic" v-model = "ramFrequency" required>
      </div>
      
<hr>
      <button class="w-100 btn btn-primary btn-lg" type="submit" v-on:click="Submit()">View similar computers </button>
    </form>

    <div v-if="view==true">
 <label for="exampleFormControlSelect1">Similar computers</label>
  <table class="table table-striped table-dark">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">motherboardDDR</th>
      <th scope="col">hddMemoryCapacity</th>
      <th scope="col">motherboardSocket</th>
      <th scope="col">motherboardName</th>

     <th scope="col">ramFrequency</th>
      <th scope="col">turboBoost</th>
      <th scope="col">ramName</th>
      <th scope="col">cpuMemorySpeed</th>

      <th scope="col">physicalCores</th>
      <th scope="col">cpuName</th>
      <th scope="col">gpuTeraflops</th>
      <th scope="col">gpuMemory</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="(gpu, index) in allComputers" :key="gpu.id">
      <th scope="row">{{index+1}}</th>
      <td>{{gpu.motherboardDDR}}</td>
      <td>{{gpu.hddMemoryCapacity}}</td>
      <td>{{gpu.motherboardSocket}}</td>
       <td>{{gpu.motherboardName}}</td>

        <td>{{gpu.ramFrequency}}</td>
      <td>{{gpu.turboBoost}}</td>
      <td>{{gpu.ramName}}</td>
       <td>{{gpu.cpuMemorySpeed}}</td>

       <td>{{gpu.physicalCores}}</td>
      <td>{{gpu.cpuName}}</td>
      <td>{{gpu.gpuTeraflops}}</td>
       <td>{{gpu.gpuMemory}}</td>
    </tr>
   
  </tbody>
</table>
</div>

  </div>


</template>

<script>
import axios from "axios";
export default{
   data(){
        return{
             motherboardDDR: 0,
             motherboardSocket: '',
             hddMemoryCapacity : 0,
             ramCapacity: 0,
             turboBoost: 0,
             cpuMemorySpeed: 0,
             ramFrequency: 0,
             gpuTeraflops: 0,
             allComputers: new Array(),
             view: false
            

        }
    },
    mounted()
    {

    },
     methods:
    {
      Submit()
      {
        axios.
        get('http://localhost:8082/Similar/'+this.motherboardDDR+'/'+this.motherboardSocket+'/'+this.hddMemoryCapacity+'/'+this.ramCapacity+'/'+this.turboBoost+'/'+this.cpuMemorySpeed+'/'+this.gpuTeraflops+'/'+this.ramFrequency)
        .then(response =>{
          this.allComputers=response.data;
          console.log("all Computers ", response.data)
        }).catch(error => {
    console.log(error.response)
          })

    }
    }
}

</script>



<style scoped>
.adventure-registration{

  width: 60%;
  horiz-align: center;
  margin-left: 20%;
  margin-top: 2%;
  border-radius: 3%;
  padding: 3%;
  background-blend-mode: lighten;
}
.removeImageBtn{
    position: absolute;
}
.double-field{
  display: flex;
  flex-direction: row;
}

.double-field .form-check{
  margin-left: 0.5%;
}

.adventure-registration .form-label{
}
</style>
