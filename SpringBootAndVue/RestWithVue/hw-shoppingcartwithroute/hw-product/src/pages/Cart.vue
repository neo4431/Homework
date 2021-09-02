<template>
  <div id="content">
    <div class="text-title"><strong>Đơn hàng của bạn: </strong></div>
    <br />
    <table>
      <thead>
        <tr>
          <th class="width5"><strong>#</strong></th>
          <th class="width45"><strong>Name</strong></th>
          <th class="width15"><strong>Manufacturer</strong></th>
          <th class="width10"><strong>Price</strong></th>
          <th class="width15"><strong>Count</strong></th>
          <th class="width10 text-end"><strong>Sub Total</strong></th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(orderLine, index) in cart.orderLines"
          :key="orderLine.product.id"
        >
          <td>{{ index + 1 }}</td>
          <td>{{ orderLine.product.name }}</td>
          <td>{{ orderLine.product.manufacturer }}</td>
          <td>{{ orderLine.product.price }}</td>
          <td>
            <button
              class="btn-count"
              type="button"
              @click="decrease(orderLine.product.id)"
            >
              &#8211;
            </button>
            &emsp; {{ orderLine.count }} &emsp;
            <button
              class="btn-count"
              type="button"
              @click="increase(orderLine.product.id)"
            >
              +
            </button>
          </td>
          <td class="text-end">
            {{ orderLine.product.price * orderLine.count }}
          </td>
        </tr>
        <tr id="raw-total">
          <td></td>
          <td></td>
          <td></td>
          <td>Raw Total</td>
          <td></td>
          <td class="text-end">{{ cart.rawTotal }}</td>
        </tr>
        <tr id="discount">
          <td></td>
          <td></td>
          <td></td>
          <td>Discount</td>
          <td></td>
          <td class="text-end">{{ cart.discount }}</td>
        </tr>
        <tr id="vat">
          <td></td>
          <td></td>
          <td></td>
          <td>VAT</td>
          <td></td>
          <td class="text-end">{{ cart.vatTax }}</td>
        </tr>
        <tr id="total">
          <td></td>
          <td></td>
          <td></td>
          <td>Total</td>
          <td></td>
          <td class="text-end">{{ cart.total }}</td>
        </tr>
      </tbody>
    </table>
    <br />
    <div id="info">
      <div class="text-title"><strong>Địa chỉ giao hàng: </strong></div>
      <br>
      <form @submit.prevent>
        <label for="name">Họ và tên: </label>
        <input type="text" name="name" id="name" value="" v-model="user.name"/>
        <span class="text-danger" v-if="nameErrorMessage">{{ nameErrorMessage }}</span>
        <label for="phoneNumber">Di động: </label>
        <input type="text" name="phoneNumber" id="phoneNumber" value="" v-model="user.phoneNumber"/>
        <span class="text-danger" v-if="phoneNumberErrorMessage">{{ phoneNumberErrorMessage }}</span>
        <label for="email">Email: </label>
        <input type="text" name="email" id="email" value="" v-model="user.email"/>
        <span class="text-danger" v-if="emailErrorMessage">{{ emailErrorMessage }}</span>
        <input id="submit" type="submit" value="Xác nhận đơn hàng" @click="checkOut()" />
      </form>
    </div>
    <router-link id="success" to="/success">go to success</router-link>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.withCredentials = true;
export default {
  name: "Cart",
  data() {
    return {
      cart: {},
      user: {
        name: "",
        phoneNumber: "",
        email: "",
      },
      countCart: 0,
      nameErrorMessage: "",
      phoneNumberErrorMessage: "",
      emailErrorMessage: "",
      errors: []
    };
  },
  methods: {
    increase(id) {
      axios.patch(`http://localhost:4000/api/cart/increase/${id}`,
        {withCredentials: true})
        .then((response) => {
          this.cart = response.data.cart;
          this.countCart = response.data.count;
          document.getElementById("count").innerText =
            "Cart(" + this.countCart + ")";
        })
        .catch((e) => {
          this.errors.push(e);
        });
    },

    decrease(id) {
      axios
        .patch(`http://localhost:4000/api/cart/decrease/${id}`, {
          withCredentials: true,
        })
        .then((response) => {
          this.cart = response.data.cart;
          this.countCart = response.data.count;
          document.getElementById("count").innerText =
            "Cart(" + this.countCart + ")";
        })
        .catch((e) => {
          this.errors.push(e);
        });
    },
    displayErrors(){
      this.nameErrorMessage =  "";
      this.phoneNumberErrorMessage = "";
      this.emailErrorMessage = "";
      this.errors.forEach(error => {
        switch (error.field) {
          case "name":
            this.nameErrorMessage = error.defaultMessage;
            break;
          case "phoneNumber":
            this.phoneNumberErrorMessage = error.defaultMessage;
            break;
          case "email":
            this.emailErrorMessage = error.defaultMessage;
            break;
        }
      });
    },
    checkOut(){
      // if(this.checkForm){
        axios.post("http://localhost:4000/api/check-out", this.user, {withCredentials: true})
        .then(response =>{
            console.log(response);
            this.nameErrorMessage =  "";
            this.phoneNumberErrorMessage = "";
            this.emailErrorMessage = "";
            this.countCart = 0;
            document.getElementById("success").click();
        })
        .catch(e => {
            this.errors = []
            this.errors = e.response.data;
            this.displayErrors();
        })
      // }
    }
  },
  created() {
    axios
      .get("http://localhost:4000/api/cart")
      .then((response) => {
        console.log(response);
        this.cart = response.data;
      })
      .catch((e) => {
        this.errors.push(e);
        console.log(this.errors);
      });
  },
};
</script>

<style scoped>
#content {
  margin-top: 80px;
  width: 80vw;
  margin-left: auto;
  margin-right: auto;
  padding-left: 20px;
  padding-right: 20px;
  background-color: #ffffff;
  overflow-x: auto;
  overflow-y: auto;
}
#info{
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: start;
}
label {
    float: left;
}
#submit {
    /* float: left; */
    margin-top: 10px;
    margin-bottom: 20px;
}
form {
  display: flex;
    flex-direction: column;
    align-items: flex-start;
  width: 100%;
}
table,
tr,
th,
td {
  border: 1px solid black;
}

table,
thead {
  width: 100%;
  text-align: left;
}
/* .grid-container {
    display: grid;
    grid-column-gap: 5px;
    grid-template-columns: auto;
}

.grid-item {
    background-color: rgba(255, 255, 255, 0.8);
    border: 1px solid rgba(0, 0, 0, 0.8);
    padding: 20px;
    font-size: 30px;
    text-align: center;
} */

.width15 {
  width: 10%;
}
.width5 {
  width: 5%;
}
.width10 {
  width: 10%;
}
.width45 {
  width: 40%;
}
.text-danger {
  color: red;
}
.btn-count {
  background: rgb(55, 55, 128);
  border-radius: 5px;
  padding: 2px 7px;
  color: #ffffff;
  display: inline-block;
  font: normal bold 14px/1 "Open Sans", sans-serif;
  text-align: center;
}
.text-end {
  text-align: end;
}
.text-title {
  font-size: 20px;
  font-weight: 500;
  float: left;
}
#success{
  display: none;
}
</style>
