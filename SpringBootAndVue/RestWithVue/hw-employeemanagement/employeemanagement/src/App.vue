<template>
  <div id="app">
    <div id="header">
      <div id="header-nav">
        <div id="nav">
          <ul class="nav-list">
            <li class="nav-item"><a href="#" @click="backHome">HOME</a></li>
            <li class="nav-item"><router-link to="/people">List People</router-link></li>
            <li class="nav-item"><router-link to="/jobs">List Jobs</router-link></li>
          </ul>
        </div>
      </div>
      <div id="language">
        <ul>
          <li><a href="">English</a></li>
          <li><a href="">Vietnamese</a></li>
        </ul>
      </div>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "App",
  methods:{
    backHome(){
      window.location = "http://localhost:8080/"
    }
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  /* -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px; */
}
html,
body {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
#header {
  display: flex;
  justify-content: space-between;
  background-color: rgb(55, 55, 128);
}

#header-nav {
  display: flex;
}

#nav .nav-list {
  display: flex;
}

#nav .nav-item {
  list-style-type: none;
  margin-left: 50px;
  line-height: 30px;
}
#nav .nav-item:hover a {
  color: rgb(126, 124, 124);
}
#nav .nav-item a {
  color: white;
  text-decoration: none;
  font-style: normal;
  font-size: 20px;
}
#language ul {
  display: flex;
}

#language ul li {
  list-style-type: none;
  line-height: 30px;
  
  font-style: normal;
  font-size: 20px;
  margin-right: 30px;
}

#language a {
  color: white;
  text-decoration: none;
}

#language li:hover a {
  color: rgb(126, 124, 124);
}
</style>
