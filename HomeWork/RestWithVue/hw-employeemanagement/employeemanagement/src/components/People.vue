<template>
  <div id="people">
    <div class="text"><router-link to="/">Thêm người dùng</router-link></div>
    <div class="text" v-if="isEmpty">
      <p>Danh sách người dùng trống!!!</p>
    </div>
    <table v-else>
      <tr class="width150">
        <th class="width30">STT</th>
        <th class="width150">ẢNH ĐẠI DIỆN</th>
        <th class="width150">HỌ VÀ TÊN</th>
        <th class="width150">NGHỀ NGHIỆP</th>
        <th class="width150">GIỚI TÍNH</th>
        <th class="width150">NGÀY SINH</th>
        <th colspan="2" class="width150">TÙY CHỌN</th>
      </tr>
      <tr class="width150" v-for="person,index in people" :key="person.id">
        <td>{{ index + 1 }}</td>
        <td class="width150">
          <img :src="require(`@/assets/photos/${person.id}.jpg`)" alt="avatar" />
        </td>
        <td class="width150">{{ person.fullName }}</td>
        <td class="width150">{{ person.job }}</td>
        <td class="width150">{{ person.gender }}</td>
        <td class="width150">{{ person.birthDate }}</td>
        <td class="setting">
          <router-link :to="`/edit/${person.id}`">Edit</router-link>
        </td>
        <td class="setting">
          <a href="" @click="deletePerson(person.id)">Delete</a>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "People",

  methods: {
    checkList(){
        if(this.people.length > 0){
            return false;
        }
        return true;
    },

    async deletePerson(id){
        await axios
        .delete(`http://localhost:4000/api/people/delete/`+ id)
        .then((response) => {
          console.log(response);
          this.created();
        })
        .catch((e) => {
          this.errors.push(e.response.data);
        });
    }
  },

  data() {
    return {
      people: [],
      isEmpty: true,
      errors: []
    };
  },

  async created() {
    const RESPONSE = await fetch("http://localhost:4000/api/people");
    const people = await RESPONSE.json();
    this.people = people;
    this.isEmpty = this.checkList();
  },
};
</script>

<style scoped>
table , tr, td ,th{
            border: 1px solid black;
        }
        table {
            margin-left: 30px;
            margin-top: 30px;
        }
        .width150 {
            width: 150px;
        }
        .width30 {
            width: 30px;
            text-align: center;
        }
        img {
            height: 100%;
        }
        .setting {
            width: 75px;
        }
        .text {
            margin-top: 20px;
            margin-left: 30px;
            font-size: 20px;
        }
</style>
