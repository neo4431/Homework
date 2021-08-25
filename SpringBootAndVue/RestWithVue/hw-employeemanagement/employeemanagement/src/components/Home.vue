<template>
  <div id="home">
    <form @submit.prevent enctype="multipart/form-data">
      <fieldset>
        <legend v-if="isInsert">REGISTED</legend>
        <legend v-else>Edit</legend>
        <div class="errors" v-if="otherErrors">{{ otherErrors }}</div>
        <input type="hidden" name="id" :value="person.id" />
        <br />
        <label for="fullName">Họ và tên: </label>
        <input
          id="input-name"
          type="text"
          name="fullName"
          v-model="person.fullName"
        />
        <div class="errors" v-if="fullNameErrors">*{{ fullNameErrors }}</div>
        <br />
        <br />
        <label for="job">Nghề nghiệp: </label>
        <select id="select" name="job" v-model="person.job">
          <option v-for="job in jobs" :key="job.id">{{ job.name }}</option>
        </select>
        <div class="errors" v-if="jobErrors">*{{ jobErrors }}</div>
        <br />
        <span>Giới tính: </span>
        <label for="male">Nam</label>
        <input
          type="radio"
          id="male"
          name="gender"
          value="male"
          v-model="person.gender"
        />&emsp;
        <label for="female">Nữ</label>
        <input
          type="radio"
          id="female"
          name="gender"
          value="female"
          v-model="person.gender"
        />
        <div class="errors" v-if="genderErrors">*{{ genderErrors }}</div>
        <br />
        <label for="birthDate">Ngày sinh: </label>
        <input
          type="date"
          name="birthDate"
          id="birthDate"
          v-model="person.birthDate"
        />
        <div class="errors" v-if="birthDateErrors">*{{ birthDateErrors }}</div>
        <br /><br />
        <label for="file"></label>
        <input name="file" type="file" ref="file" multiple="multiple" />
        <div class="errors" v-if="fileErrors">*{{ fileErrors }}</div>
        <br /><br />
        <button type="submit" @click="submitForm()">Submit</button>
      </fieldset>
    </form>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Home",

  data() {
    return {
      person: {},
      jobs: [],
      isInsert: true,
      errors: [],
      fullNameErrors: "",
      genderErrors: "",
      birthDateErrors: "",
      jobErrors: "",
      fileErrors: "",
      otherErrors: "",
    };
  },
  async created() {
    await axios
      .get(`http://localhost:4000/api/jobs`)
      .then((response) => {
        this.jobs = response.data;
      })
      .catch((e) => {
        console.log(e);
      });

    if (this.$route.params.id !== undefined) {
      this.isInsert = false;
      await axios
        .get("http://localhost:4000/api/people/" + this.$route.params.id)
        .then((response) => {
          this.person = response.data;
        })
        .catch((e) => {
          this.errors.push(e.response.data.message);
          console.log(this.errors);
        });
    }
  },
  methods: {
    checkForm() {
      if (this.person.fullName === undefined) {
        this.fullNameErrors = "Không được để trống trường này";
      } else if (
        this.person.fullName.length < 5 ||
        this.person.fullName.length > 30
      ) {
        this.fullNameErrors = "Họ và tên tối thiểu 5 ký tự và tối đa 30 ký tự";
      } else {
        this.fullNameErrors = "";
      }
      if (this.person.job === undefined) {
        this.jobErrors = "Không được để trống trường này";
      } else {
        this.jobErrors = "";
      }
      if (this.person.gender === undefined) {
        this.genderErrors = "Không được để trống trường này";
      } else {
        this.genderErrors = "";
      }
      if (this.person.birthDate === undefined) {
        this.birthDateErrors = "Không được để trống trường này";
      } else {
        this.birthDateErrors = "";
      }
      if(this.$refs.file.files.length === 0){
        this.fileErrors = "Chưa có ảnh đại diện"
      } else {
        this.fileErrors = ""
      }
      if (
        this.fullNameErrors === "" &&
        this.jobErrors === "" &&
        this.genderErrors === "" &&
        this.birthDateErrors === "" &&
        this.fileErrors === ""
      ) {
        return true;
      }
      return false;
    },

    async submitForm() {
      if (this.checkForm()) {
        let formData = new FormData();
        formData.append("file", this.$refs.file.files[0]);
        formData.append("fullName", this.person.fullName);
        formData.append("job", this.person.job);
        formData.append("gender", this.person.gender);
        formData.append("birthDate", this.person.birthDate);
        if (this.isInsert) {
          await axios
            .post(`http://localhost:4000/api/people/add`, formData, {
              headers: {
                "content-type": "multipart/form-data",
              },
            })
            .then((response) => {
              response.statusText = "Thêm người dùng thành công";
              console.log(response);
              alert(response.statusText);
              window.location =
                "http://localhost:8080/people/" + response.data.id;
            })
            .catch((e) => {
              this.errors.push(e.response.data);
              console.log(this.errors);
              this.displayErrors();
            });
        } else {
          await axios
            .put(`http://localhost:4000/api/people/update/${this.$route.params.id}`, formData, {
              headers: {
                "content-type": "multipart/form-data",
              },
            })
            .then((response) => {
              response.statusText = "update người dùng thành công";
              console.log(response);
              alert(response.statusText);
              window.location =
                "http://localhost:8080/people/" + response.data.id;
            })
            .catch((e) => {
              this.errors.push(e.response.data);
              console.log(this.errors);
            });
        }
      }
    },
  },
};
</script>

<style scoped>
span {
  margin-right: 20px;
}
input {
  margin-top: 20px;
}
#home {
  margin-left: 60px;
}
form {
  margin-top: 40px;
  margin-left: auto;
  margin-right: auto;
}
legend {
  text-align: center;
  font-size: 20px;
}
fieldset {
  max-width: 25%;
  width: 350px;
}
.errors {
  color: red;
}
</style>
