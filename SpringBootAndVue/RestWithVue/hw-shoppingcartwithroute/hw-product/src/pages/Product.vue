<template>
  <div id="content">
    <div class="container py-5">
      <div class="row text-center text-white mb-5">
        <div class="col-lg-7 mx-auto">
          <h1 class="display-4">Product List</h1>
          <div id="search-bar">
            <div id="sort">
              Sắp xếp theo giá sản phẩm:
              <select name="selectSort" id="select-sort" @change="sort">
                <option value="default">Mặc định</option>
                <option value="asc">Giá tăng dần</option>
                <option value="desc">Giá giảm dần</option>
              </select>
            </div>
            <form @submit.prevent>
              <input
                type="search"
                name="search"
                id="search"
                placeholder="Nhập tên sản phẩm"
                v-model="keyword"
              />
              <input type="submit" value="Search" @click="search()" />
            </form>
          </div>
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
                  <router-link
                    class="detail"
                    :to="'/product-detail/' + product.id"
                    v-else
                  >
                    <h5 class="mt-0 font-weight-bold mb-2">
                      {{ product.name }}
                    </h5>
                  </router-link>
                  <div
                    class="loading"
                    id="load-category"
                    v-if="isLoading"
                  ></div>
                  <p class="font-italic text-muted mb-0 small" v-else>
                    Thương hiệu: {{ product.manufacturer }}
                  </p>
                  <div
                    class="d-flex align-items-center justify-content-between mt-1"
                  >
                    <h6
                      class="font-weight-bold my-2 li-loading"
                      v-if="isLoading"
                    ></h6>
                    <h6 class="font-weight-bold my-2" v-else>
                      {{ product.price }} VND
                    </h6>
                    <ul class="list-inline small">
                      <li
                        class="list-inline-item m-0 li-loading"
                        v-if="isLoading"
                      >
                        <i class="fa fa-star text-success"></i>
                      </li>
                      <li class="list-inline-item m-0" v-else>
                        <button
                          id="btn-add-to-cart"
                          @click="addToCart(product.id)"
                        >
                          Add to cart
                        </button>
                      </li>
                    </ul>
                  </div>
                </div>
                <img
                  :src="require(`@/assets/loading.jpg`)"
                  alt="Generic placeholder image"
                  width="300"
                  class="ml-lg-5 order-1 order-lg-2"
                  v-if="isLoading"
                />
                <img
                  :src="require(`@/assets/${product.photo}`)"
                  alt="Generic placeholder image"
                  width="300"
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
import axios from "axios";
axios.defaults.withCredentials = true;
export default {
  name: "Product",
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
        "http://localhost:4000/api/products/search?keyword=" +
          this.keyword +
          "&sort=" +
          sort
      );
      const products = await RESPONSE.json();
      this.isLoading = false;
      this.products = products;
      console.log(this.products);
      console.log(sort);
    },
    addToCart(id) {
      axios
        .post(`http://localhost:4000/api/add-to-cart/${id}`,{withCredentials:true})
        .then((response) => {
          console.log(response);
          this.countCart = response.data;
          document.getElementById("count").innerText =
            "Cart(" + this.countCart + ")";
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },

  data() {
    return {
      products: [{}, {}, {}],
      isLoading: true,
      keyword: "",
      image: require("@/assets/loading.jpg"),
      countCart: 0,
    };
  },

  async created() {
    const RESPONSE = await fetch("http://localhost:4000/api/products");
    const products = await RESPONSE.json();
    console.log(products);
    this.products = products.list;
    this.countCart = products.count;
    document.getElementById("count").innerText =
            "Cart(" + this.countCart + ")";
    this.isLoading = false;
  },
};
</script>

<style>
#content {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 50px;
}
#search-bar {
  display: flex;
  justify-content: space-between;
}
body {
  background: linear-gradient(to right, #c04848, #480048);
  max-height: 100vh;
}

.text-gray {
  color: #aaa;
}

img {
  height: 150px;
  width: 200px;
}
#sort {
  display: inline-block;

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
.detail {
  font-size: 16px;
}
#btn-add-to-cart {
  background: rgb(88, 85, 85);
  border-radius: 5px;
  padding: 5px 12px;
  color: #ffffff;
  display: inline-block;
  font: normal bold 14px/1 "Open Sans", sans-serif;
  text-align: center;
}
</style>
