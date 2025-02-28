package net.ethernity.lucky.client.renderer;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.entity.TrotumanEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.*;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.UseAction;

public class TrotumanRenderer extends BipedEntityRenderer<TrotumanEntity, PlayerEntityModel<TrotumanEntity>> {
    private static final Identifier TROTUMAN_TEXTURE = Identifier.of(LuckyWilly.MOD_ID, "textures/entity/trotuman.png");

    public TrotumanRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new PlayerEntityModel<>(ctx.getPart(EntityModelLayers.PLAYER), false), 0.5F);
        this.addFeature(
                new ArmorFeatureRenderer<>(
                        this,
                        new ArmorEntityModel(ctx.getPart(EntityModelLayers.PLAYER_INNER_ARMOR)),
                        new ArmorEntityModel(ctx.getPart(EntityModelLayers.PLAYER_OUTER_ARMOR)),
                        ctx.getModelManager()
                )
        );
        this.addFeature(new StuckArrowsFeatureRenderer<>(ctx, this));
    }

    @Override
    protected boolean hasLabel(TrotumanEntity mobEntity) {
        return true;
    }

    public void render(TrotumanEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        this.setModelPose(entity);
        super.render(entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    private void setModelPose(TrotumanEntity player) {
        PlayerEntityModel<TrotumanEntity> playerEntityModel = this.getModel();
        playerEntityModel.setVisible(true);
        BipedEntityModel.ArmPose armPose = getArmPose(player, Hand.MAIN_HAND);
        BipedEntityModel.ArmPose armPose2 = getArmPose(player, Hand.OFF_HAND);
        if (armPose.isTwoHanded())
            armPose2 = player.getOffHandStack().isEmpty() ? BipedEntityModel.ArmPose.EMPTY : BipedEntityModel.ArmPose.ITEM;

        playerEntityModel.rightArmPose = armPose;
        playerEntityModel.leftArmPose = armPose2;
    }

    private static BipedEntityModel.ArmPose getArmPose(TrotumanEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isEmpty()) {
            return BipedEntityModel.ArmPose.EMPTY;
        } else {
            if (player.getActiveHand() == hand && player.getItemUseTimeLeft() > 0) {
                UseAction useAction = itemStack.getUseAction();
                if (useAction == UseAction.BLOCK)
                    return BipedEntityModel.ArmPose.BLOCK;
                if (useAction == UseAction.BOW)
                    return BipedEntityModel.ArmPose.BOW_AND_ARROW;
                if (useAction == UseAction.SPEAR)
                    return BipedEntityModel.ArmPose.THROW_SPEAR;
                if (useAction == UseAction.CROSSBOW && hand == player.getActiveHand())
                    return BipedEntityModel.ArmPose.CROSSBOW_CHARGE;
                if (useAction == UseAction.SPYGLASS)
                    return BipedEntityModel.ArmPose.SPYGLASS;
                if (useAction == UseAction.TOOT_HORN)
                    return BipedEntityModel.ArmPose.TOOT_HORN;
                if (useAction == UseAction.BRUSH)
                    return BipedEntityModel.ArmPose.BRUSH;
            } else if (!player.handSwinging && itemStack.isOf(Items.CROSSBOW) && CrossbowItem.isCharged(itemStack)) {
                return BipedEntityModel.ArmPose.CROSSBOW_HOLD;
            }

            return BipedEntityModel.ArmPose.ITEM;
        }
    }


    @Override
    public Identifier getTexture(TrotumanEntity entity) {
        return TrotumanRenderer.TROTUMAN_TEXTURE;
    }
}
