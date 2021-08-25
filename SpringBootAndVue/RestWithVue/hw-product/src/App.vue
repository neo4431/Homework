<template>
  <div id="app">
    <div id="search-bar">
      <form action="" method="get">
        <input
          type="search"
          name="search"
          id="search"
          placeholder="Nhập tên sản phẩm"
          v-model="keyword"
        />
        <input type="button" value="Search" @click="search" />
      </form>
    </div>
    <br />
    <br />
    <br />
    <div id="sort">
      Sắp xếp theo giá sản phẩm:
      <select name="selectSort" id="select-sort" @change="sort">
        <option value="default">Mặc định</option>
        <option value="asc">Giá tăng dần</option>
        <option value="desc">Giá giảm dần</option>
      </select>
    </div>
    <div class="container py-5">
      <div class="row text-center text-white mb-5">
        <div class="col-lg-7 mx-auto">
          <h1 class="display-4">Product List</h1>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <!-- List group-->
          <ul class="list-group shadow">
            <!-- list group item-->
            <li
              class="list-group-item"
              v-for="product in products"
              :key="product.id"
            >
              <!-- Custom content-->
              <div
                class="media align-items-lg-center flex-column flex-lg-row p-3"
              >
                <div class="media-body order-2 order-lg-1">
                  <div
                    class="loading"
                    style="height:20px; width: 350px;"
                    v-if="isLoading"
                  ></div>
                  <h5 class="mt-0 font-weight-bold mb-2" v-else>
                    {{ product.name }}
                  </h5>
                  <div
                    class="loading"
                    id="load-category"
                    v-if="isLoading"
                  ></div>
                  <p class="font-italic text-muted mb-0 small" v-else>
                    Category: {{ product.category }}
                  </p>
                  <div
                    class="d-flex align-items-center justify-content-between mt-1"
                  >
                    <h6 class="font-weight-bold my-2 li-loading" v-if="isLoading"></h6>
                    <h6 class="font-weight-bold my-2" v-else>{{ product.price }} $</h6>
                    <ul class="list-inline small">
                      <li
                        class="list-inline-item m-0 li-loading"
                        v-if="isLoading"
                      >
                        <i class="fa fa-star text-success"></i>
                      </li>
                      <li class="list-inline-item m-0" v-else>
                        <i class="fa fa-star text-success"
                          >Quantity: {{ product.quantity }}</i
                        >
                      </li>
                    </ul>
                  </div>
                </div>
                <img
                  :src="require(`@/assets/loading.jpg`)"
                  alt="Generic placeholder image"
                  width="200"
                  class="ml-lg-5 order-1 order-lg-2"
                  v-if="isLoading"
                />
                <img
                  :src="require(`@/assets/${product.image}`)"
                  alt="Generic placeholder image"
                  width="200"
                  class="ml-lg-5 order-1 order-lg-2"
                  v-else
                />
              </div>
              <!-- End -->
            </li>
            <!-- End -->
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "App",
  methods: {
    sort() {
      let sort = document.getElementById("select-sort").value;
      if (sort == "asc") {
        this.products.sort((a, b) => a.price - b.price);
      } else if (sort == "desc") {
        this.products.sort((a, b) => b.price - a.price);
      } else {
        this.products.sort((a, b) => a.id - b.id);
      }
    },
    async search() {
      this.isLoading = true;
      let sort = document.getElementById("select-sort").value;
      const RESPONSE = await fetch(
        "http://localhost:4000/api/products?keyword=" + this.keyword + "&sort=" + sort
      );
      const products = await RESPONSE.json();
      this.isLoading = false;
      this.products = products;
      // this.products = this.products.sort();
    },
  },

  data() {
    return {
      products: [{}, {}, {}],
      isLoading: true,
      keyword: "",
      image: require("@/assets/loading.jpg"),
      
    };
  },

  async created() {
    const RESPONSE = await fetch("http://localhost:4000/api/products");
    const products = await RESPONSE.json();
    this.isLoading = false;
    this.products = products;
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 30px;
}
#search-bar {
  float: right;
  margin-right: 30px;
  /* background-color: gray; */
}
body {
  background: linear-gradient(to right, #c04848, #480048);
  min-height: 100vh;
}

.text-gray {
  color: #aaa;
}

img {
  height: 170px;
  width: 140px;
}
#sort {
  color: white;
}
#load-category {
  height: 16px;
  width: 20%;
  margin-top: 5px;
}
.loading {
  margin-left: auto;
  margin-right: auto;
  background-color: gray;
}
.li-loading {
  height: 16px;
  width: 100px;
  background-color: gray;
}
</style>
