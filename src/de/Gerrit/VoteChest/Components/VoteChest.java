package de.Gerrit.VoteChest.Components;


import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * Created by gerritharmeling on 06.12.17.
 */
public class VoteChest implements Chest{

    public VoteChest(){

    }

    @Override
    public Inventory getBlockInventory() {
        return null;
    }

    @Override
    public String getCustomName() {
        return null;
    }

    @Override
    public void setCustomName(String s) {

    }

    @Override
    public Block getBlock() {
        return null;
    }

    @Override
    public MaterialData getData() {
        return null;
    }

    @Override
    public Material getType() {
        return null;
    }

    @Override
    public int getTypeId() {
        return 0;
    }

    @Override
    public byte getLightLevel() {
        return 0;
    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getZ() {
        return 0;
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public Location getLocation(Location location) {
        return null;
    }

    @Override
    public Chunk getChunk() {
        return null;
    }

    @Override
    public void setData(MaterialData materialData) {

    }

    @Override
    public void setType(Material material) {

    }

    @Override
    public boolean setTypeId(int i) {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean update(boolean b) {
        return false;
    }

    @Override
    public boolean update(boolean b, boolean b1) {
        return false;
    }

    @Override
    public byte getRawData() {
        return 0;
    }

    @Override
    public void setRawData(byte b) {

    }

    @Override
    public boolean isPlaced() {
        return false;
    }

    @Override
    public boolean isLocked() {
        return false;
    }

    @Override
    public String getLock() {
        return null;
    }

    @Override
    public void setLock(String s) {

    }

    @Override
    public Inventory getInventory() {
        return null;
    }

    @Override
    public void setMetadata(String s, MetadataValue metadataValue) {

    }

    @Override
    public List<MetadataValue> getMetadata(String s) {
        return null;
    }

    @Override
    public boolean hasMetadata(String s) {
        return false;
    }

    @Override
    public void removeMetadata(String s, Plugin plugin) {

    }
}

