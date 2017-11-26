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

    delete(id) {

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
