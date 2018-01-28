# Steps to use Flatbuffers

FlatBuffers is a project from Google that allows you to encode and decode data at great speed. Here’s a quick tutorial on how to install the compiler flatc, a dependency for developers using FlatBuffers.

In my previous article i have already described Flatbuffers.

## Install Flatbuffers
At the beginning we have to get flatc — FlatBuffers compiler. It can be built from source code hosted in Google’s flatbuffers repository. Let’s download/clone it.

```
$ git clone https://github.com/google/flatbuffers.git
$ cd flatbuffers
```
Run the command on the basis of your platform

```
$ cmake -G "Unix Makefiles"
$ cmake -G "Visual Studio 10"
$ cmake -G "Xcode"
```

Whole build process is described on FlatBuffers Building documentation. Retrieving a Windows binary can be done through the FlatBuffers releases page. Grab the latest exe file and you’re all set. Note that the binary must be used with the corresponding release from that page. I have added flatc file inside schema folder in my sample github proejct.

## Schema file

Now we have to prepare schema file which defines data structures we want to de-/serialize. This schema will be used with flatc to create Java models and to transform JSON into Flatbuffer binary file.

Here is a part of our JSON file:

JSON file
Now write schema file with extension *.fbs. If you are beginner you can lean writing schema here. So now you have created your JSON and schema file.

## Schema file
Great! Its time to generate bin and flatbuffers model. Following command will generate java POJO file for flatbuffers from JSON.
```
$ ./flatc -j -b repos_schema.fbs repos_json.json
```
Now keep all generated java files in java folder and bin file in your raw folder where you can read these file.
Write program to read resource JSON file and parse them in java object as well as read flatbuffers bin file using generated java class and compare their performance.

Flatbuffers smaple check

