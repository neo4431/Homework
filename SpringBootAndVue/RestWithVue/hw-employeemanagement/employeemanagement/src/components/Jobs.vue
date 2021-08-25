<template>
  <div id="job">
    <h1>Danh sách nghề nghiệp</h1>

    <form @submit.prevent>
      <input
        type="text"
        placeholder="Tên nghề nghiệp mới"
        name="name"
        v-model="name"
      />
      <button type="submit" @click="addJob()">Thêm</button>
      <br />
      <br />
      <div class="errors" v-if="errors.length">*{{ errors[0] }}</div>
    </form>

    <table v-if="jobs.length">
      <thead>
        <th>#</th>
        <th>Nghề nghiệp</th>
      </thead>
      <tbody>
        <tr v-for="(job, index) in jobs" :key="job.id">
          <td>{{ index + 1 }}</td>
          <td>{{ job.name }}</td>
        </tr>
      </tbody>
    </table>
    <div id="jobs-empty" v-else>Danh sách nghề nghiệp trống!!!</div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Jobs",
  data() {
    return {
      jobs: [],
      name: "",
      errors: []
    };
  },

  methods: {
    async addJob() {
      this.errors = [];
      let job = {
        name: this.name.trim(),
      };
      await axios
        .post("http://localhost:4000/api/jobs/add", job)
        .then(() => {
          this.getList();
        })
        .catch( e => {
          if (this.name.trim() === "") {
            this.errors.push(e.response.data.errors[0].defaultMessage);
          } else {
            this.errors.push(e.response.data);
          }
        });
    },

    async getList(){
      await axios
      .get("http://localhost:4000/api/jobs")
      .then((response) => {
        this.jobs = response.data;
      })
      .catch((e) => {
        console.log(e);
      });
    }
  },

  created() {
    this.getList()
  },
};
</script>

<style scoped>
table,
tr,
th,
td {
  border: 1px solid black;
}

table {
  margin-left: 40px;
  margin-top: 40px;
}

h1,
p {
  margin-left: 20px;
}

tr,
th,
td {
  width: 30px;
}

#content {
  display: flex;
}
form {
  margin-left: 20px;
}
.errors {
  color: red;
}
#jobs-empty {
  font-size: 20px;
  margin-top: 30px;
  margin-left: 30px;
}
</style>
