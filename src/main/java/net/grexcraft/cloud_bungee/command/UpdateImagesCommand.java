package net.grexcraft.cloud_bungee.command;

import net.grexcraft.cloud_bungee.client.CloudWebClient;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class UpdateImagesCommand extends Command {

    public UpdateImagesCommand() {
        super("cloudupdateimages", "grexcraft.cloud.update_images");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        System.out.println("update command");

        CloudWebClient.fetchImages();
    }
}
