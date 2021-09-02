<template>
  <div class="container">
    <div class="card">
      <div class="row">
        <aside class="col-sm-5 border-right">
          <article class="gallery-wrap">
            <div class="img-big-wrap">
              <div>
                <a href="#"
                  ><img :src="require(`@/assets/${product.photo}`)"
                /></a>
              </div>
            </div>
          </article>
          <!-- gallery-wrap .end// -->
        </aside>
        <aside class="col-sm-7">
          <article class="card-body p-5">
            <h3 class="title mb-3">{{ product.name }}</h3>

            <p class="price-detail-wrap">
              <span class="price h3 text-warning">
                <span class="currency">Giá: </span><span class="num">{{ product.price }} VND</span>
              </span>
            </p>
            <!-- price-detail-wrap .// -->
            <dl class="item-property">
              <dt>Description</dt>
              <dd>
                <p>
                  Here goes description consectetur adipisicing elit, sed do
                  eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
                  enim ad minim veniam, quis nostrud exercitation ullamco
                </p>
              </dd>
            </dl>
            <dl class="param param-feature">
              <dt>Manufacturer</dt>
              <dd>{{ product.manufacturer }}</dd>
            </dl>
            <!-- item-property-hor .// -->
          </article>
          <!-- card-body.// -->
        </aside>
        <!-- col.// -->
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: "ProductDetail",
  data() {
    return {
      product: {},
      errors: []
    }
  },

  created() {
    axios.get(`http://localhost:4000/api/products/${this.$route.params.id}`)
    .then(response => {
      this.product = response.data;
      // console.log(response.data);
      console.log(this.product);
    
    }).catch(e => {
      this.errors.push(e);
      console.log(this.errors)
    })
  },
};

</script>

<style scoped>


.gallery-wrap .img-big-wrap img {
    /* height: 450px; */
	height: 100%;
    width: 100%;
    display: inline-block;
    cursor: zoom-in;
}


.gallery-wrap .img-small-wrap .item-gallery {
    width: 60px;
    height: 60px;
    border: 1px solid #ddd;
    margin: 7px 2px;
    display: inline-block;
    overflow: hidden;
}

.gallery-wrap .img-small-wrap {
    text-align: center;
}
.gallery-wrap .img-small-wrap img {
    max-width: 100%;
    max-height: 100%;
    object-fit: cover;
    border-radius: 4px;
    cursor: zoom-in;
}
.container{
  margin-top: 70px;
}
aside.col-sm-5.border-right {
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
