<template>
  <div id="person-detail">
      <h1>Thông tin chi tiết</h1>
      <div class="errors" v-if="errors.length">{{ errors[0] }}</div>
    <table v-else>
        <tr>
      <td>
        <img :src="require(`@/assets/photos/${person.id}.jpg`)" alt="avatar" />
      </td>
      <td>
        <p>Họ và tên: </p>
        <p>Nghề nghiệp: </p>
        <p>Giới tính: </p>
        <p>Ngày sinh: </p>
      </td>
      <td>
        <p>{{ person.fullName }}</p>
        <p>{{ person.job }}</p>
        <p>{{ person.gender }}</p>
        <p>{{ person.birthDate }}</p>
      </td>
      </tr>
    </table>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "PersonDetail",
  data() {
      return {
          person: {},
          errors: []
      }
  },

  async created() {
    await axios
      .get("http://localhost:4000/api/people/" + this.$route.params.id)
      .then((response) => {
        this.person = response.data;
      })
      .catch((e) => {
        this.errors.push(e.response.data.message);
        console.log(this.errors)
      });
  },
};
</script>

<style scoped>
h1{
    margin-left: 30px;
}
table {
  margin-left: 50px;
  margin-top: 50px;
}

td {
  width: 100px;
  padding-left: 30px;
}

img {
  max-width: 250px;
  max-height: 250px;
}
.errors{
  color: red;
  margin-left: 40px;
  font-size: 30px;
}
</style>
