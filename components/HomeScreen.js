import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  Button,
  View,ListView, TouchableOpacity,
  TextInput,
  Linking
} from 'react-native';
import {Bar} from 'react-native-pathjs-charts';

import storageService from '../services/StorageService';
import { styles } from '../styles';

export default class HomeScreen extends Component {

  constructor(props) 
  {
      super(props);
      this.state = {
        products: [],
        dataSource: null,
      };
    }

   async componentDidMount() {
      this.ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
      const products = await storageService.getProducts();
      this.setState({
        products: products,
        dataSource: this.ds.cloneWithRows(products),
      });
    }

    async update(newProducts){
      this.setState({
        products: newProducts,
        dataSource: this.ds.cloneWithRows(newProducts)
      });
      await storageService.setProducts(newProducts);
    }

    renderRow(rowData)
    {
       return (
        <View style={styles.welcome} >
          <TouchableOpacity  onPress={() => this.props.navigation.navigate('Update', {item: rowData, update: this.update.bind(this), products: this.state.products})}>
            <Text style={styles.instructions}> {rowData.name} Price: {rowData.price}  Quantity: {rowData.quantity}
            </Text>
          </TouchableOpacity>
        </View>
      );
    }

  render() {
    if (!this.state.dataSource) {
      return (
        <View style={styles.container}>
        </View>
      );
    }

    let data = this.state.products.map((element) => [{"v": element.quantity, "name":element.name}]) 
    let options = {
      width: 300,
      height: 300,
      margin: {
        top: 20,
        left: 25,
        bottom: 50,
        right: 20
      },
      color: '#2980B9',
      gutter: 20,
      animate: {
        type: 'oneByOne',
        duration: 200,
        fillTransition: 3
      },
      axisX: {
        showAxis: true,
        showLines: true,
        showLabels: true,
        showTicks: true,
        zeroAxis: false,
        orient: 'bottom',
        label: {
          fontFamily: 'Arial',
          fontSize: 8,
          fontWeight: true,
          fill: '#34495E',
          rotate: 45
        }
      },
      axisY: {
        showAxis: true,
        showLines: true,
        showLabels: true,
        showTicks: true,
        zeroAxis: false,
        orient: 'left',
        label: {
          fontFamily: 'Arial',
          fontSize: 8,
          fontWeight: true,
          fill: '#34495E'
        }
      }
    }
    return (
      <View style={styles.container}>
        <ListView
        dataSource={this.state.dataSource}
        renderRow={this.renderRow.bind(this)}
        />
        <Button
          onPress={() => this.props.navigation.navigate('Create', {update: this.update.bind(this), products: this.state.products})}
          title="Add new"
        />

        <Bar data={data} options={options} accessorKey='v'/>

        <TextInput style={{fontSize: 20}} value={this.state.email} placeholder="Email:"
                  onChangeText={(text) => this.setState({email: text})}>
        </TextInput>
        <Button
          onPress={() => Linking.openURL('mailto:' + this.state.email)}
          title="Send mail"
        />

      </View>

       
      );
  }
}
