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
import styles from './styles';
import HomeScreen from './components/HomeScreen';
import UpdateScreen from './components/UpdateScreen';
import CreateScreen from './components/CreateScreen';


const RootNavigator = StackNavigator({
  Home: {
    screen: HomeScreen,
    navigationOptions: {
      headerTitle: 'Home',
    },
  },
  Update: {
    screen: UpdateScreen,
    navigationOptions: {
      headerTitle: 'Update',
    },
  },
  Create: {
    screen: CreateScreen,
    navigationOptions: {
      headerTitle: 'Create',
    },
  },
});

export default RootNavigator;
