<script lang="ts">
    let {
        content
    }: {
        content: string
    } = $props();
</script>

<div class="flex flex-1 text-center">
    <span class="w-full">{content}</span>
</div>
