<template>
  <div id="app">
    <div id="menu">
      <div id="logo">DEMO SHOPPING CART</div>
      <div id="nav">
        <router-link class="nav-item" to="/">Home</router-link>
        <router-link id="count" class="nav-item" to="/cart">Cart(0)</router-link>
      </div>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style scoped>
#menu {
  position: fixed;
  left: 0;
  right: 0;
  top: 0;
  height: 50px;
  background-color: rgba(53, 3, 11, 0.5);
  z-index: 2;
  display: flex;
}
#nav{
  display: flex;
  width: 80%;
  justify-content: space-between;
  align-items: center;
}
#logo {
  font-size: 26px;
  line-height: 50px;
  font-weight: 700;
  color: white;
  margin-left: 20px;
  margin-right: 20px;
  width: 300px;
  /* text-overflow: ellipsis; */
  white-space: nowrap;
}
.nav-item {
  color: white;
   font-size: 20px;
  line-height: 48px;
  font-weight: 600;
  border-left: 1px solid white;
  border-right: 1px solid white;
  padding: 0px 20px;
}
#btn-checkout{
    background:    white;
    border-radius: 11px;
    padding:       5px 12px;
    color:         #7e0874;
    display:       inline-block;
    font:          normal bold 16px/1 "Open Sans", sans-serif;
    text-align:    center;
}
</style>
