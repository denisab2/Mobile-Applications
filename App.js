/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  Button,
  View,ListView, TouchableOpacity,
  TextInput
} from 'react-native';
import { StackNavigator } from 'react-navigation';

let products=[
  {name:'egg',price:'2', quantity:'20'},
  {name:'bread',price:'5', quantity:'2'},
  {name:'milk',price:'6', quantity:'5'}
]


  
  /**<View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
    <Text>Home Screen</Text>
    <Button
      onPress={() => navigation.navigate('Details')}
      title="Go to details"
    />
  </View>*/

class DetailsScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: ''
    };
  }
  render() {
    return (
      <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
        <Text>Details Screen</Text>
        <TextInput value={this.state.name || this.props.navigation.state.params.item.name} onChangeText={(text) => this.setState({name: text})}></TextInput>
        <Button
          onPress={() => this.props.navigation.state.params.fct(this.props.navigation.state.params.item, this.state.name)}
          title="Save"
        />
      </View>
    );
  }
}

class HomeScreen extends Component<{}> {

  constructor(props) 
  {
      super(props);
      const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
      this.state = {
        dataSource: ds.cloneWithRows(products), 
      };
    }

      functie(id, name) {
            var temp = [];
            temp = products.slice();
          temp[0].name = name;
          this.state = {
            dataSource: this.state.dataSource.cloneWithRows(temp), 
          };
      }

     renderRow(rowData)
      {
       return (
              <View style={styles.welcome} >
              <TouchableOpacity  onPress={() => this.props.navigation.navigate('Details', {item: rowData, fct: this.functie.bind(this)})}>
                <Text style={styles.instructions}> {rowData.name}   Price: {rowData.price}  Quantity: {rowData.quantity}
                </Text>

              </TouchableOpacity>
              </View>
            );
      }

  render() {
    return (
      <View style={styles.container}>
        <ListView 
        dataSource={this.state.dataSource}
        renderRow={this.renderRow.bind(this)}
        />
      </View>
      );
  }
}

const RootNavigator = StackNavigator({
  Home: {
    screen: HomeScreen,
    navigationOptions: {
      headerTitle: 'Home',
    },
  },
  Details: {
    screen: DetailsScreen,
    navigationOptions: {
      headerTitle: 'Details',
    },
  },
});

export default RootNavigator;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
    fontSize: 20,
  },
});

