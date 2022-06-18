import os, RLPy, math
from winreg import *

def run_script():
    print ("Hello World")
    #RLPy.RUi.ShowMessageBox("Your First Python Plugin", "Hello World", RLPy.EMsgButton_Ok)
    #-- Get Characters from current scene --#
    avatar_list = RLPy.RScene.GetAvatars()
    #-- Get First Character --#
    avatar = avatar_list[0]
    #-- Get Material --#
    material_component  = avatar.GetMaterialComponent()
    print(type(material_component).__name__)
    mesh_list = avatar.GetMeshNames()
    mesh_name = mesh_list[0]
    for i in mesh_list:
        if "shirt" in i:
            mesh_name = i
            break
    print(mesh_name)
    material_list = material_component.GetMaterialNames(mesh_name)
    material_name = material_list[0]
    print(material_name)

    # Create a key
    key = RLPy.RKey()
    #key.SetTime(RLPy.RTime(1200))
    # Add a diffuse key value
    material_component.AddDiffuseKey(key, mesh_name, material_name, RLPy.RRgb.RED)
    RLPy.RScene.Show(avatar)

    #Load image to material channel
    # texture_channel = RLPy.EMaterialTextureChannel_Diffuse
    # image_file = "C:\Users\DIP2020\Desktop\google_logo.png"
    # result = material_component.LoadImageToTexture(mesh_name, material_name, texture_channel, image_file)