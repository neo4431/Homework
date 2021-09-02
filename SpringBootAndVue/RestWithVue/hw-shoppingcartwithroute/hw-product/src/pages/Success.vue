<template>
  <div id="content">
    <h1 style="text-align: center;">
      Cám ơn bạn đã sử dụng dịch vụ của chúng tôi
    </h1>
    <div>
      <router-link to="/">Bấm vào đây</router-link> để quay trở lại trang chủ
    </div>
  </div>
</template>

<script>

export default {
  name: "Success",
  created() {
      document.getElementById("count").innerText =
            "Cart(" + 0 + ")";
  },
};
</script>

<style scoped>
#content {
  width: 80vw;
  margin-left: auto;
  margin-right: auto;
  color: white;
  margin-top: 80px;
}
</style>
